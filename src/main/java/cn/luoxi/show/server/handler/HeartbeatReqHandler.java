package cn.luoxi.show.server.handler;

import org.tio.core.ChannelContext;

import java.io.UnsupportedEncodingException;

import cn.luoxi.show.common.ShowPacket;
import cn.luoxi.show.common.inft.AbsShowBsHandler;
import cn.luoxi.show.common.packets.LoginReqBody;

/**
 * 心跳处理
 *
 * @author 夏智峰
 * @create 2017-10-30 13:18
 */
public class HeartbeatReqHandler extends AbsShowBsHandler<LoginReqBody> {
  @Override
  public Class<LoginReqBody> bodyClass() {
    return LoginReqBody.class;
  }

  @Override
  public Object handler(ShowPacket packet, LoginReqBody bsBody, ChannelContext context) throws UnsupportedEncodingException {
    //心跳消息，什么也不用做
    return null;
  }
}
