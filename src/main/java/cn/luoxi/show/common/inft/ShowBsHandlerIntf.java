package cn.luoxi.show.common.inft;

import org.tio.core.ChannelContext;

import cn.luoxi.show.common.ShowPacket;

public interface ShowBsHandlerIntf {
  public Object handler(ShowPacket packet, ChannelContext context) throws Exception;
}
