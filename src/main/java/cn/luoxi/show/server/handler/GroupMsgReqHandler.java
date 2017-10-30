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
import cn.luoxi.show.common.packets.GroupMsgReqBody;
import cn.luoxi.show.common.packets.GroupMsgRespBody;

/**
 * 群聊消息请求
 *
 * @author 夏智峰
 * @create 2017-10-30 15:55
 */
public class GroupMsgReqHandler extends AbsShowBsHandler<GroupMsgReqBody> {
  private static Logger log = LoggerFactory.getLogger(JoinGroupReqHandler.class);
  @Override
  public Class<GroupMsgReqBody> bodyClass() {
    return GroupMsgReqBody.class;
  }

  @Override
  public Object handler(ShowPacket packet, GroupMsgReqBody bsBody, ChannelContext context) throws UnsupportedEncodingException {
    log.info("收到群聊消息响应：{}", Json.toJson(bsBody));
    ShowSessionContext sessionContext = (ShowSessionContext) context.getAttribute();

    GroupMsgRespBody respBody = new GroupMsgRespBody();
    respBody.setFromName(sessionContext.getUserId());
    respBody.setToGroup(bsBody.getToGroupName());
    respBody.setText(bsBody.getText());

    ShowPacket showPacket = new ShowPacket();
    showPacket.setType(Type.GROUP_MSG_RESP);
    showPacket.setBody(Json.toJson(respBody).getBytes(Const.CHARSET));

    Aio.sendToGroup(context.getGroupContext(), bsBody.getToGroupName(), showPacket);
    return null;
  }
}
