package cn.luoxi.show.server;

import org.tio.server.AioServer;
import org.tio.server.ServerGroupContext;
import org.tio.server.intf.ServerAioHandler;
import org.tio.server.intf.ServerAioListener;

import java.io.IOException;

import cn.luoxi.show.common.Const;

/**
 * 启动服务端
 *
 * @author 夏智峰
 * @create 2017-10-30 11:26
 */
public class ShowServerStarter {
  //编码、解码、消息处理
  static ServerAioHandler handler = new ShowServerAioHandler();
  //事件监听，可以为空
  static ServerAioListener listener = new ShowServerAioListener();
  static ServerGroupContext groupContext = new ServerGroupContext(handler, listener);

  static AioServer aioServer = new AioServer(groupContext);

  static String serverIp = null;
  static int serverPort = Const.PORT;

  public static void main(String[] args) throws IOException {
    aioServer.start(serverIp, serverPort);
  }

}
