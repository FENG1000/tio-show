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
import cn.luoxi.show.common.packets.RetreatGroupReqBody;
import cn.luoxi.show.common.packets.RetreatGroupRespBody;

/**
 * 退群请求消息处理
 *
 * @author 夏智峰
 * @create 2017-10-31 9:45
 */
public class RetreatGroupReqHandler extends AbsShowBsHandler<RetreatGroupReqBody> {
  private static Logger log = LoggerFactory.getLogger(RetreatGroupReqHandler.class);

  @Override
  public Class<RetreatGroupReqBody> bodyClass() {
    return RetreatGroupReqBody.class;
  }

  @Override
  public Object handler(ShowPacket packet, RetreatGroupReqBody bsBody, ChannelContext context) throws UnsupportedEncodingException {
    log.info("收到退群请求：{}", Json.toJson(bsBody));
    RetreatGroupRespBody respBody = new RetreatGroupRespBody();
    respBody.setCode(Const.SUCCESS);
    respBody.setMsg("已经成功退出群：" + bsBody.getGroupName());
    respBody.setGroupName(bsBody.getGroupName());
    Aio.unbindGroup(bsBody.getGroupName(), context);

    ShowPacket showPacket = new ShowPacket();
    showPacket.setType(Type.RETREAT_GROUP_RESP);
    showPacket.setBody(Json.toJson(respBody).getBytes(Const.CHARSET));
    Aio.send(context, showPacket);
    return null;
  }
}
