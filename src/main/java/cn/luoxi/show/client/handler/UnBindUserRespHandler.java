package cn.luoxi.show.client.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.ChannelContext;

import java.io.UnsupportedEncodingException;

import cn.luoxi.show.common.Const;
import cn.luoxi.show.common.ShowPacket;
import cn.luoxi.show.common.inft.AbsShowBsHandler;
import cn.luoxi.show.common.packets.UnBindUserRespBody;

/**
 * 退出登录响应消息处理
 *
 * @author 夏智峰
 * @create 2017-10-31 13:58
 */
public class UnBindUserRespHandler extends AbsShowBsHandler<UnBindUserRespBody> {
  private static Logger log = LoggerFactory.getLogger(UnBindUserRespHandler.class);
  @Override
  public Class<UnBindUserRespBody> bodyClass() {
    return UnBindUserRespBody.class;
  }

  @Override
  public Object handler(ShowPacket packet, UnBindUserRespBody bsBody, ChannelContext context) throws UnsupportedEncodingException {
    if (Const.SUCCESS.equals(bsBody.getCode())) {
      log.info(bsBody.getMsg());
    } else {
      log.info("退出登录成功");
    }
    return null;
  }
}
