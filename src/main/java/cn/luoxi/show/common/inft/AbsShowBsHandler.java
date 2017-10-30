package cn.luoxi.show.common.inft;

import org.tio.core.ChannelContext;
import org.tio.utils.json.Json;

import java.io.UnsupportedEncodingException;

import cn.luoxi.show.common.Const;
import cn.luoxi.show.common.ShowPacket;
import cn.luoxi.show.common.packets.BaseBody;

/**
 * @author 夏智峰
 * @create 2017-10-30 11:03
 */
public abstract class AbsShowBsHandler<T extends BaseBody> implements ShowBsHandlerIntf{
  public abstract Class<T> bodyClass();
  @Override
  public Object handler(ShowPacket packet, ChannelContext context) throws Exception {
    String jsonStr = null;
    T bsBody = null;
    if (packet.getBody() != null) {
      jsonStr = new String(packet.getBody(), Const.CHARSET);
      bsBody = Json.toBean(jsonStr, bodyClass());
    }
    return handler(packet, bsBody, context);
  }
  public abstract Object handler(ShowPacket packet, T bsBody, ChannelContext context) throws UnsupportedEncodingException;
}
