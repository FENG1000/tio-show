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
import cn.luoxi.show.common.packets.P2PReqBody;
import cn.luoxi.show.common.packets.P2PRespBody;

/**
 * 点对点消息处理
 *
 * @author 夏智峰
 * @create 2017-10-30 13:56
 */
public class P2PReqHandler extends AbsShowBsHandler<P2PReqBody>{
  private static Logger log = LoggerFactory.getLogger(P2PReqHandler.class);
  @Override
  public Class<P2PReqBody> bodyClass() {
    return P2PReqBody.class;
  }

  @Override
  public Object handler(ShowPacket packet, P2PReqBody bsBody, ChannelContext context) throws UnsupportedEncodingException {
    log.info("收到点对点请求消息：{}", Json.toJson(bsBody));

    ShowSessionContext sessionContext = (ShowSessionContext) context.getAttribute();

    P2PRespBody respBody = new P2PRespBody();
    respBody.setFromUserId(sessionContext.getUserId());
    respBody.setText(bsBody.getText());

    ShowPacket reqPacket = new ShowPacket();
    reqPacket.setType(Type.P2P_RESP);
    reqPacket.setBody(Json.toJson(respBody).getBytes(Const.CHARSET));

    Aio.sendToUser(context.getGroupContext(), bsBody.getToUserId(), reqPacket);

    return null;
  }
}
