package cn.luoxi.show.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.ChannelContext;
import org.tio.core.intf.Packet;
import org.tio.server.intf.ServerAioListener;

import cn.luoxi.show.common.ShowSessionContext;

/**
 * 监听器
 *
 * @author 夏智峰
 * @create 2017-10-30 11:23
 */
public class ShowServerAioListener implements ServerAioListener {
  private static Logger log = LoggerFactory.getLogger(ShowServerAioListener.class);
  //关闭连接后
  @Override
  public void onAfterClose(ChannelContext channelContext, Throwable throwable, String s, boolean
          b) throws Exception {

  }

  //连接后
  @Override
  public void onAfterConnected(ChannelContext channelContext, boolean b, boolean b1) throws Exception {
    //连接上时创建session
    channelContext.setAttribute(new ShowSessionContext());
  }

  //收到消息后
  @Override
  public void onAfterReceived(ChannelContext channelContext, Packet packet, int i) throws
          Exception {

  }

  //发送消息后
  @Override
  public void onAfterSent(ChannelContext channelContext, Packet packet, boolean b) throws
          Exception {

  }

  //关闭连接前
  @Override
  public void onBeforeClose(ChannelContext channelContext, Throwable throwable, String s, boolean b) {

  }
}
