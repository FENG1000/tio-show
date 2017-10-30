package cn.luoxi.show.client.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.ChannelContext;
import org.tio.utils.json.Json;

import java.io.UnsupportedEncodingException;

import cn.luoxi.show.common.ShowPacket;
import cn.luoxi.show.common.inft.AbsShowBsHandler;
import cn.luoxi.show.common.packets.P2PRespBody;

/**
 * 点对点响应消息处理
 *
 * @author 夏智峰
 * @create 2017-10-30 14:10
 */
public class P2PRespHandler extends AbsShowBsHandler<P2PRespBody> {
  private static Logger log = LoggerFactory.getLogger(P2PRespHandler.class);

  @Override
  public Class<P2PRespBody> bodyClass() {
    return P2PRespBody.class;
  }

  @Override
  public Object handler(ShowPacket packet, P2PRespBody bsBody, ChannelContext context) throws UnsupportedEncodingException {
    log.info("收到点对点响应消息："+ Json.toJson(bsBody));
    return null;
  }
}
