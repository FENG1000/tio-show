package cn.luoxi.show.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.client.intf.ClientAioListener;
import org.tio.core.ChannelContext;
import org.tio.core.intf.Packet;

import cn.luoxi.show.common.ShowSessionContext;

/**
 * 监听
 *
 * @author 夏智峰
 * @create 2017-10-30 13:27
 */
public class ShowClientAioListener implements ClientAioListener {
  private static Logger log = LoggerFactory.getLogger(ShowClientAioListener.class);
  //客户端关闭后触发
  @Override
  public void onAfterClose(ChannelContext channelContext, Throwable throwable, String s, boolean
          b) throws Exception {

  }

  //客户端连接上触发
  @Override
  public void onAfterConnected(ChannelContext channelContext, boolean b, boolean b1) throws Exception {
    channelContext.setAttribute(new ShowSessionContext());
  }

  //监听收到收到消息后
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
