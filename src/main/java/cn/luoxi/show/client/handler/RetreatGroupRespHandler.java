package cn.luoxi.show.client.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.ChannelContext;

import java.io.UnsupportedEncodingException;

import cn.luoxi.show.common.Const;
import cn.luoxi.show.common.ShowPacket;
import cn.luoxi.show.common.inft.AbsShowBsHandler;
import cn.luoxi.show.common.packets.RetreatGroupRespBody;

/**
 * 退群消息相应
 *
 * @author 夏智峰
 * @create 2017-10-31 9:56
 */
public class RetreatGroupRespHandler extends AbsShowBsHandler<RetreatGroupRespBody> {
  private static Logger log = LoggerFactory.getLogger(RetreatGroupRespHandler.class);
  @Override
  public Class<RetreatGroupRespBody> bodyClass() {
    return RetreatGroupRespBody.class;
  }

  @Override
  public Object handler(ShowPacket packet, RetreatGroupRespBody bsBody, ChannelContext context) throws UnsupportedEncodingException {
    if (Const.SUCCESS.equals(bsBody.getCode())) {
      log.info(bsBody.getMsg());
    } else {
      log.info("退群失败");
    }
    return null;
  }
}
