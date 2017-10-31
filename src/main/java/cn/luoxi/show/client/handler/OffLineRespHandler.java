package cn.luoxi.show.client.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.ChannelContext;

import java.io.UnsupportedEncodingException;

import cn.luoxi.show.common.Const;
import cn.luoxi.show.common.ShowPacket;
import cn.luoxi.show.common.inft.AbsShowBsHandler;
import cn.luoxi.show.common.packets.OffLineRespBody;

/**
 * 踢掉其他设备响应消息处理
 *
 * @author 夏智峰
 * @create 2017-10-31 13:23
 */
public class OffLineRespHandler extends AbsShowBsHandler<OffLineRespBody> {
  private static Logger log = LoggerFactory.getLogger(OffLineRespHandler.class);
  @Override
  public Class<OffLineRespBody> bodyClass() {
    return OffLineRespBody.class;
  }

  @Override
  public Object handler(ShowPacket packet, OffLineRespBody bsBody, ChannelContext context) throws UnsupportedEncodingException {
    if (Const.SUCCESS.equals(bsBody.getCode())) {
      log.info(bsBody.getMsg());
    } else {
      log.info("踢出失败！！");
    }
    return null;
  }
}
