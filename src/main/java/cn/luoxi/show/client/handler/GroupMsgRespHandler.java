package cn.luoxi.show.client.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.ChannelContext;
import org.tio.utils.json.Json;

import java.io.UnsupportedEncodingException;

import cn.luoxi.show.common.ShowPacket;
import cn.luoxi.show.common.inft.AbsShowBsHandler;
import cn.luoxi.show.common.packets.GroupMsgRespBody;
import cn.luoxi.show.server.handler.JoinGroupReqHandler;

/**
 * 群聊消息响应消息处理
 *
 * @author 夏智峰
 * @create 2017-10-30 16:04
 */
public class GroupMsgRespHandler extends AbsShowBsHandler<GroupMsgRespBody> {
  private static Logger log = LoggerFactory.getLogger(JoinGroupReqHandler.class);
  @Override
  public Class<GroupMsgRespBody> bodyClass() {
    return GroupMsgRespBody.class;
  }

  @Override
  public Object handler(ShowPacket packet, GroupMsgRespBody bsBody, ChannelContext context) throws UnsupportedEncodingException {
    log.info("响应群聊消息：{}", Json.toJson(bsBody));
    return null;
  }
}
