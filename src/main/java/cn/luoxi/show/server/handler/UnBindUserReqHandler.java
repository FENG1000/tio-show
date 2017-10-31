package cn.luoxi.show.server.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.Aio;
import org.tio.core.ChannelContext;
import org.tio.utils.json.Json;

import java.io.UnsupportedEncodingException;

import cn.luoxi.show.common.Const;
import cn.luoxi.show.common.ShowPacket;
import cn.luoxi.show.common.ShowSessionContext;
import cn.luoxi.show.common.Type;
import cn.luoxi.show.common.inft.AbsShowBsHandler;
import cn.luoxi.show.common.packets.UnBindUserReqBody;
import cn.luoxi.show.common.packets.UnBindUserRespBody;

/**
 * 退出登录请求消息处理
 *
 * @author 夏智峰
 * @create 2017-10-31 14:01
 */
public class UnBindUserReqHandler extends AbsShowBsHandler<UnBindUserReqBody> {
  private static Logger log = LoggerFactory.getLogger(UnBindUserReqHandler.class);
  @Override
  public Class<UnBindUserReqBody> bodyClass() {
    return UnBindUserReqBody.class;
  }

  @Override
  public Object handler(ShowPacket packet, UnBindUserReqBody bsBody, ChannelContext context) throws UnsupportedEncodingException {
    log.info("收到退出登录请求");

    ShowSessionContext sessionContext = (ShowSessionContext) context.getAttribute();

    UnBindUserRespBody respBody = new UnBindUserRespBody();
    respBody.setUserId(sessionContext.getUserId());
    respBody.setCode(Const.SUCCESS);
    respBody.setMsg("账号"+ sessionContext.getUserId() +"退出成功");

    Aio.unbindUser(context);

    ShowPacket showPacket = new ShowPacket();
    showPacket.setType(Type.UN_BIND_USER_RESP);
    showPacket.setBody(Json.toJson(respBody).getBytes(Const.CHARSET));

    Aio.send(context, showPacket);
    return null;
  }
}
