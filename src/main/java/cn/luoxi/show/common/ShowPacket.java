package cn.luoxi.show.common;

import org.tio.core.intf.Packet;

/**
 * 消息包
 *
 * @author 夏智峰
 * @create 2017-10-30 10:48
 */
public class ShowPacket extends Packet {
  /**
   * 消息类型
   */
  private byte type;
  private byte[] body;

  //初始化packet的值
  public ShowPacket() {
    super();
  }

  public byte getType() {
    return type;
  }

  public void setType(byte type) {
    this.type = type;
  }

  public byte[] getBody() {
    return body;
  }

  public void setBody(byte[] body) {
    this.body = body;
  }

  @Override
  public String logstr() {
    return "" + type;
  }
}
