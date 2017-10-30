package cn.luoxi.show.client.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.ChannelContext;
import org.tio.utils.json.Json;

import java.io.UnsupportedEncodingException;

import cn.luoxi.show.common.ShowPacket;
import cn.luoxi.show.common.ShowSessionContext;
import cn.luoxi.show.common.inft.AbsShowBsHandler;
import cn.luoxi.show.common.packets.LoginRespBody;

/**
 * 登陆响应消息处理
 *
 * @author 夏智峰
 * @create 2017-10-30 13:07
 */
public class LoginRespHandler extends AbsShowBsHandler<LoginRespBody> {
  private static Logger log = LoggerFactory.getLogger(LoginRespHandler.class);
  @Override
  public Class<LoginRespBody> bodyClass() {
    return LoginRespBody.class;
  }

  @Override
  public Object handler(ShowPacket packet, LoginRespBody bsBody, ChannelContext context) throws UnsupportedEncodingException {
    log.info("收到登陆响应消息：" + Json.toJson(bsBody));
    if (LoginRespBody.Code.SUCCESS.equals(bsBody.getCode())) {
      ShowSessionContext sessionContext = (ShowSessionContext) context.getAttribute();
      sessionContext.setToken(bsBody.getToken());
      log.info("登陆成功，token是：" + bsBody.getToken());
    }
    return null;
  }
}
