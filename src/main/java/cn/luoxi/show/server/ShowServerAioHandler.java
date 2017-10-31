package cn.luoxi.show.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.ChannelContext;
import org.tio.core.intf.Packet;
import org.tio.server.intf.ServerAioHandler;

import java.util.HashMap;
import java.util.Map;

import cn.luoxi.show.common.ShowAbsAioHandler;
import cn.luoxi.show.common.ShowPacket;
import cn.luoxi.show.common.Type;
import cn.luoxi.show.common.inft.AbsShowBsHandler;
import cn.luoxi.show.server.handler.GroupMsgReqHandler;
import cn.luoxi.show.server.handler.JoinGroupReqHandler;
import cn.luoxi.show.server.handler.LoginReqHandler;
import cn.luoxi.show.server.handler.OffLineReqHandler;
import cn.luoxi.show.server.handler.P2PReqHandler;
import cn.luoxi.show.server.handler.RetreatGroupReqHandler;
import cn.luoxi.show.server.handler.UnBindUserReqHandler;

/**
 * 服务端消息处理
 *
 * @author 夏智峰
 * @create 2017-10-30 11:17
 */
public class ShowServerAioHandler extends ShowAbsAioHandler implements ServerAioHandler {
  private static Logger log = LoggerFactory.getLogger(ShowServerAioHandler.class);
  private static Map<Byte, AbsShowBsHandler<?>> handlerMap = new HashMap<>();
  static {
    handlerMap.put(Type.LOGIN_REQ, new LoginReqHandler());
    handlerMap.put(Type.HEART_BEAT_REQ, new LoginReqHandler());
    handlerMap.put(Type.P2P_REQ, new P2PReqHandler());
    handlerMap.put(Type.JOIN_GROUP_REQ, new JoinGroupReqHandler());
    handlerMap.put(Type.GROUP_MSG_REQ, new GroupMsgReqHandler());
    handlerMap.put(Type.RETREAT_GROUP_REQ, new RetreatGroupReqHandler());
    handlerMap.put(Type.OFF_LINE_REQ, new OffLineReqHandler());
    handlerMap.put(Type.UN_BIND_USER_REQ, new UnBindUserReqHandler());
  }
  @Override
  public void handler(Packet packet, ChannelContext channelContext) throws Exception {
    ShowPacket showPacket = (ShowPacket) packet;
    Byte type = showPacket.getType();
    AbsShowBsHandler<?> showBsHandler = handlerMap.get(type);
    if(showBsHandler == null) {
      log.error("{}，找不到消息处理类型，type:{}", channelContext, type);
      return;
    }
    showBsHandler.handler(showPacket, channelContext);
    return;
  }
}
