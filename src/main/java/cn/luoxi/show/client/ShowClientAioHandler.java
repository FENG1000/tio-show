package cn.luoxi.show.client;

import org.tio.client.intf.ClientAioHandler;
import org.tio.core.ChannelContext;
import org.tio.core.intf.Packet;

import java.util.HashMap;
import java.util.Map;

import cn.luoxi.show.client.handler.GroupMsgRespHandler;
import cn.luoxi.show.client.handler.JoinGroupRespHandler;
import cn.luoxi.show.client.handler.LoginRespHandler;
import cn.luoxi.show.client.handler.P2PRespHandler;
import cn.luoxi.show.common.ShowAbsAioHandler;
import cn.luoxi.show.common.ShowPacket;
import cn.luoxi.show.common.Type;
import cn.luoxi.show.common.inft.AbsShowBsHandler;

/**
 * 客户端消息处理
 *
 * @author 夏智峰
 * @create 2017-10-30 13:12
 */
public class ShowClientAioHandler extends ShowAbsAioHandler implements ClientAioHandler {
  private static Map<Byte, AbsShowBsHandler<?>> handlerMap = new HashMap<>();
  static {
    handlerMap.put(Type.LOGIN_RESP, new LoginRespHandler());
    handlerMap.put(Type.P2P_RESP, new P2PRespHandler());
    handlerMap.put(Type.JOIN_GROUP_RESP, new JoinGroupRespHandler());
    handlerMap.put(Type.GROUP_MSG_RESP, new GroupMsgRespHandler());
  }
  @Override
  public void handler(Packet packet, ChannelContext channelContext) throws Exception {
    ShowPacket showPacket = (ShowPacket) packet;
    Byte type = showPacket.getType();
    AbsShowBsHandler<?> showBsHandler = handlerMap.get(type);
    showBsHandler.handler(showPacket, channelContext);
    return;
  }

  /**
   * 此方法如果返回null，框架层面则不会发心跳；如果返回非null，框架层面会定时发本方法返回的消息包
   */
  @Override
  public Packet heartbeatPacket() {
    return new ShowPacket();
  }
}
