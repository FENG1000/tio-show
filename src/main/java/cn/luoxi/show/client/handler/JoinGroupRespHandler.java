package cn.luoxi.show.client.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.ChannelContext;

import java.io.UnsupportedEncodingException;

import cn.luoxi.show.common.ShowPacket;
import cn.luoxi.show.common.inft.AbsShowBsHandler;
import cn.luoxi.show.common.packets.JoinGroupRespBody;

/**
 * 入群响应消息处理
 *
 * @author 夏智峰
 * @create 2017-10-30 15:28
 */
public class JoinGroupRespHandler extends AbsShowBsHandler<JoinGroupRespBody> {
  private static Logger log = LoggerFactory.getLogger(JoinGroupRespBody.class);
  @Override
  public Class<JoinGroupRespBody> bodyClass() {
    return JoinGroupRespBody.class;
  }

  @Override
  public Object handler(ShowPacket packet, JoinGroupRespBody bsBody, ChannelContext context) throws UnsupportedEncodingException {
    if (JoinGroupRespBody.Code.SUCCESS.equals(bsBody.getCode())) {
      log.info("入群{}成功", bsBody.getGroupName());
    }
    return null;
  }
}
