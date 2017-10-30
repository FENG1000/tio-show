package cn.luoxi.show.server.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.Aio;
import org.tio.core.ChannelContext;
import org.tio.utils.json.Json;

import java.io.UnsupportedEncodingException;

import cn.luoxi.show.common.Const;
import cn.luoxi.show.common.ShowPacket;
import cn.luoxi.show.common.Type;
import cn.luoxi.show.common.inft.AbsShowBsHandler;
import cn.luoxi.show.common.packets.JoinGroupReqBody;
import cn.luoxi.show.common.packets.JoinGroupRespBody;

/**
 * 进群请求消息处理
 *
 * @author 夏智峰
 * @create 2017-10-30 15:16
 */
public class JoinGroupReqHandler extends AbsShowBsHandler<JoinGroupReqBody> {
  private static Logger log = LoggerFactory.getLogger(JoinGroupReqHandler.class);
  @Override
  public Class<JoinGroupReqBody> bodyClass() {
    return JoinGroupReqBody.class;
  }

  @Override
  public Object handler(ShowPacket packet, JoinGroupReqBody bsBody, ChannelContext context) throws UnsupportedEncodingException {
    log.info("收到入群请求");
    JoinGroupRespBody respBody = new JoinGroupRespBody();
    respBody.setCode(JoinGroupRespBody.Code.SUCCESS);
    respBody.setGroupName(bsBody.getGroupName());
    Aio.bindGroup(context, bsBody.getGroupName());

    ShowPacket reqPacket = new ShowPacket();
    reqPacket.setType(Type.JOIN_GROUP_RESP);
    reqPacket.setBody(Json.toJson(respBody).getBytes(Const.CHARSET));

    Aio.send(context, reqPacket);
    return null;
  }
}
