package cn.luoxi.show.client;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.client.AioClient;
import org.tio.client.ClientChannelContext;
import org.tio.client.ClientGroupContext;
import org.tio.client.ReconnConf;
import org.tio.client.intf.ClientAioHandler;
import org.tio.client.intf.ClientAioListener;
import org.tio.core.Aio;
import org.tio.core.Node;
import org.tio.utils.json.Json;

import java.io.UnsupportedEncodingException;
import java.util.Scanner;

import cn.luoxi.show.common.Const;
import cn.luoxi.show.common.ShowPacket;
import cn.luoxi.show.common.Type;
import cn.luoxi.show.common.packets.GroupMsgReqBody;
import cn.luoxi.show.common.packets.JoinGroupReqBody;
import cn.luoxi.show.common.packets.LoginReqBody;
import cn.luoxi.show.common.packets.OffLineReqBody;
import cn.luoxi.show.common.packets.P2PReqBody;
import cn.luoxi.show.common.packets.RetreatGroupReqBody;
import cn.luoxi.show.common.packets.UnBindUserReqBody;


/**
 * 客户端启动
 *
 * @author 夏智峰
 * @create 2017-10-30 13:29
 */
public class ShowClientStarter {
  private static Scanner sc = new Scanner(System.in);
  private static Logger log = LoggerFactory.getLogger(ShowClientStarter.class);
  //连接服务器的ip地址端口号
  private static Node serverNode = new Node("192.168.1.127", Const.PORT);
  //编码、解码、消息处理
  private static ClientAioHandler aioHandler = new ShowClientAioHandler();
  //监听
  private static ClientAioListener aioListener = new ShowClientAioListener();
  //断开连接后自动连接的，不想自动连接的请设置为null
  private static ReconnConf reconnConf = new ReconnConf(1000L);
  //公用的上下文对象
  private static ClientGroupContext groupContext = new ClientGroupContext(aioHandler, aioListener, reconnConf);

  private static AioClient aioClient = null;
  private static ClientChannelContext clientChannelContext;

  public static void main(String[] args) throws Exception {
    aioClient = new AioClient(groupContext);
    clientChannelContext = aioClient.connect(serverNode);

    read();

  }

  //菜单栏
  public static void read() throws Exception{
    StringBuffer buffer = new StringBuffer();
    int i = 1;
    buffer.append("欢迎使用控制台聊天软件\n");
    buffer.append(i++ +"、输入'L name password'登陆\n");
    buffer.append(i++ +"、输入'P toName text'向toName发送消息\n");
    buffer.append(i++ +"、输入'J groupName'申请加入groupName\n");
    buffer.append(i++ +"、输入'G groupName text'发送群聊消息\n");
    buffer.append(i++ +"、输入'R groupName'退出群聊\n");
    buffer.append(i++ +"、输入'U'下线\n");
    buffer.append(i++ +"、输入'O userId'退出指定账号登录登录\n");
    buffer.append(i++ +"、输入'E'退出程序");
    //buffer.append(i++ +"、\n");
    log.info(buffer.toString());
    write();
  }
  public static void write() throws Exception {
    String str = null;
    //设置一个标量当需要跳出while循环
    while (true) {
      str= sc.nextLine();
      String[] command = StringUtils.split(str, " ");
      switch (command[0]) {
        case "L":
          login(command[1], command[2]);
          break;
        case "P":
          p2pMsg(command[1], command[2]);
          break;
        case "J":
          joinGroup(command[1]);
          break;
        case "G":
          groupMsg(command[1], command[2]);
          break;
        case "R":
          retreatGroup(command[1]);
          break;
        case "O":
          offLine(command[1]);
          break;
        case "U":
          unBindUser();
          break;
        case "E":
          log.info("感谢使用");
          System.exit(0);
        default:
          read();
            break;
      }
    }
  }

  /**
   * 登陆
   * @param name  登陆名称
   * @param passWord 密码
   * @throws Exception
   */
  public static void login(String name, String passWord) throws UnsupportedEncodingException {

    LoginReqBody reqBody = new LoginReqBody();
    reqBody.setLoginName(name);
    reqBody.setPassWord(passWord);
    ShowPacket showPacket = new ShowPacket();
    showPacket.setType(Type.LOGIN_REQ);
    showPacket.setBody(Json.toJson(reqBody).getBytes(Const.CHARSET));

    Aio.send(clientChannelContext, showPacket);
  }

  /**
   * 点对点聊天
   * @param toName 被发送人名字
   * @param text 消息内容
   * @throws UnsupportedEncodingException
   */
  public static void p2pMsg(String toName, String text) throws UnsupportedEncodingException {
    P2PReqBody reqBody = new P2PReqBody();
    reqBody.setToUserId(toName);
    reqBody.setText(text);

    ShowPacket packet = new ShowPacket();
    packet.setType(Type.P2P_REQ);
    packet.setBody(Json.toJson(reqBody).getBytes(Const.CHARSET));

    Aio.send(clientChannelContext, packet);
  }

  /**
   * 申请入群
   * @param groupName 群名称
   */
  public static void joinGroup(String groupName) throws UnsupportedEncodingException {
    JoinGroupReqBody reqBody = new JoinGroupReqBody();
    reqBody.setGroupName(groupName);

    ShowPacket packet = new ShowPacket();
    packet.setType(Type.JOIN_GROUP_REQ);
    packet.setBody(Json.toJson(reqBody).getBytes(Const.CHARSET));

    Aio.send(clientChannelContext, packet);
  }

  /**
   * 群聊
   * @param groupName 群名称
   * @param text 消息内容
   */
  public static void groupMsg(String groupName, String text) throws UnsupportedEncodingException {
    GroupMsgReqBody reqBody = new GroupMsgReqBody();
    reqBody.setToGroupName(groupName);
    reqBody.setText(text);

    ShowPacket packet = new ShowPacket();
    packet.setType(Type.GROUP_MSG_REQ);
    packet.setBody(Json.toJson(reqBody).getBytes(Const.CHARSET));

    Aio.send(clientChannelContext, packet);
  }
  /**
   * 退群
   * @param groupName 群名称
   * @throws UnsupportedEncodingException
   */
  public static void retreatGroup(String groupName) throws UnsupportedEncodingException {
    RetreatGroupReqBody reqBody = new RetreatGroupReqBody();
    reqBody.setGroupName(groupName);

    ShowPacket showPacket = new ShowPacket();
    showPacket.setType(Type.RETREAT_GROUP_REQ);
    showPacket.setBody(Json.toJson(reqBody).getBytes(Const.CHARSET));
    Aio.send(clientChannelContext, showPacket);
  }

  /**
   * 退出登录
   */
  public static void unBindUser() throws UnsupportedEncodingException {
    UnBindUserReqBody reqBody = new UnBindUserReqBody();

    ShowPacket packet = new ShowPacket();
    packet.setType(Type.UN_BIND_USER_REQ);
    packet.setBody(Json.toJson(reqBody).getBytes(Const.CHARSET));

    Aio.send(clientChannelContext, packet);
  }

  /**
   * 输入id可以踢出其他人账号登录
   * @param userId 用户id
   */
  public static void offLine(String userId) throws UnsupportedEncodingException {
    OffLineReqBody reqBody = new OffLineReqBody();
    reqBody.setUserId(userId);

    ShowPacket showPacket = new ShowPacket();
    showPacket.setType(Type.OFF_LINE_REQ);
    showPacket.setBody(Json.toJson(reqBody).getBytes(Const.CHARSET));

    Aio.send(clientChannelContext, showPacket);
  }

}
