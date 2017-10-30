package cn.luoxi.show.server.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.Aio;
import org.tio.core.ChannelContext;
import org.tio.utils.json.Json;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.atomic.AtomicLong;

import cn.luoxi.show.common.Const;
import cn.luoxi.show.common.ShowPacket;
import cn.luoxi.show.common.ShowSessionContext;
import cn.luoxi.show.common.Type;
import cn.luoxi.show.common.inft.AbsShowBsHandler;
import cn.luoxi.show.common.packets.LoginReqBody;
import cn.luoxi.show.common.packets.LoginRespBody;

/**
 * 登陆响应消息处理
 *
 * @author 夏智峰
 * @create 2017-10-30 11:07
 */
public class LoginReqHandler extends AbsShowBsHandler<LoginReqBody> {
  private static Logger log = LoggerFactory.getLogger(LoginReqHandler.class);
  AtomicLong tokenSeq = new AtomicLong();

  @Override
  public Class<LoginReqBody> bodyClass() {
    return LoginReqBody.class;
  }

  @Override
  public Object handler(ShowPacket packet, LoginReqBody bsBody, ChannelContext context) throws UnsupportedEncodingException {
    log.info("收到登陆请求");
    LoginRespBody respBody = new LoginRespBody();
    respBody.setCode(LoginRespBody.Code.SUCCESS);
    respBody.setToken(newToken());

    //将用户名设置为用户id
    String userId = bsBody.getLoginName();
    //绑定用户
    Aio.bindUser(context, userId);

    //将用户id放入session
    ShowSessionContext sessionContext = (ShowSessionContext) context.getAttribute();
    sessionContext.setUserId(userId);

    ShowPacket showPacket = new ShowPacket();
    showPacket.setType(Type.LOGIN_RESP);
    showPacket.setBody(Json.toJson(respBody).getBytes(Const.CHARSET));

    Aio.send(context, showPacket);
    return null;
  }

  private String newToken() {
    return System.currentTimeMillis() + "_" + tokenSeq.incrementAndGet();
  }
}
