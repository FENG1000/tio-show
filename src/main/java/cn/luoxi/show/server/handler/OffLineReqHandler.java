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
import cn.luoxi.show.common.packets.OffLineReqBody;
import cn.luoxi.show.common.packets.OffLineRespBody;

/**
 * 踢掉其他设备请求消息处理
 *
 * @author 夏智峰
 * @create 2017-10-31 13:13
 */
public class OffLineReqHandler extends AbsShowBsHandler<OffLineReqBody> {
  private static Logger log = LoggerFactory.getLogger(OffLineReqHandler.class);
  @Override
  public Class<OffLineReqBody> bodyClass() {
    return OffLineReqBody.class;
  }

  @Override
  public Object handler(ShowPacket packet, OffLineReqBody bsBody, ChannelContext context) throws UnsupportedEncodingException {
    log.info("收到退出登录请求：" + Json.toJson(bsBody));
    ShowSessionContext sessionContext = (ShowSessionContext) context.getAttribute();
    OffLineRespBody respBody = new OffLineRespBody();
    respBody.setUserId(bsBody.getUserId());
    respBody.setCode(Const.SUCCESS);
    respBody.setMsg("您的账号“"+ bsBody.getUserId() +"”已被"+ sessionContext.getUserId() + "踢出");

    Aio.unbindUser(context.getGroupContext(), bsBody.getUserId());

    ShowPacket showPacket = new ShowPacket();
    showPacket.setType(Type.OFF_LINT_RESP);
    showPacket.setBody(Json.toJson(respBody).getBytes(Const.CHARSET));

    Aio.send(context, showPacket);
    return null;
  }
}
