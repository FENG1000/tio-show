package cn.luoxi.show.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.ChannelContext;
import org.tio.core.GroupContext;
import org.tio.core.exception.AioDecodeException;
import org.tio.core.intf.AioHandler;
import org.tio.core.intf.Packet;

import java.nio.ByteBuffer;

/**
 * 编码解码
 *
 * @author 夏智峰
 * @create 2017-10-30 10:52
 */
public abstract class ShowAbsAioHandler implements AioHandler {
  private static Logger log = LoggerFactory.getLogger(ShowAbsAioHandler.class);
  /**
   * 解码：把接收到的ByteBuffer，解码成应用可以识别的业务消息包
   * 总的消息结构：消息头 + 消息体
   * 消息头的结构：4个字节，存储消息体的长度
   * 消息体的结构：对象的json串的byte[]
   * 根据ByteBuffer解码成业务需要的Packet对象.
   * 如果收到的数据不全，导致解码失败，请返回null，在下次消息来时框架层会自动续上前面的收到的数据
   */
  @Override
  public Packet decode(ByteBuffer byteBuffer, ChannelContext channelContext) throws AioDecodeException {
    //可读取数据的长度 = limit(byte[]的实际存储大小) + position(byte[]从哪个下表开始都取)
    int readableLength = byteBuffer.limit() - byteBuffer.position();
    //判断消息内容是否完整，当小于消息类型加消息头的长度则证明不完整
    if (readableLength < Const.HEADER_LENGHT) {
      return null;
    }

    //消息类型
    byte type = byteBuffer.get();

    int bodyLength = byteBuffer.getInt();
    //判断有没有消息内容
    if (bodyLength < 0) {
      throw new AioDecodeException("bodyLength["+ bodyLength +"] is not right, remote:" + channelContext.getClientNode());
    }

    //整个消息体的长度
    int neededLength = Const.HEADER_LENGHT + bodyLength;
    int test = readableLength - neededLength;
    //判断可读的消息体长度够不够读取整个消息体的长度,剩下的bytebuffer组不了消息体
    if (test < 0) {
      return null;
    } else {
      ShowPacket showPacket = new ShowPacket();
      showPacket.setType(type);
      if (bodyLength > 0) {
        byte[] dst = new byte[bodyLength];
        //取到消息内容
        byteBuffer.get(dst);
        showPacket.setBody(dst);
      }
      return showPacket;
    }
  }
  /**
   * 编码：把业务消息包编码为可以发送的ByteBuffer
   * 总的消息结构：消息头+消息体
   * 消息头结构：4个字节，存储消息体的总长度
   * 消息体结构：对象的json串的byte[]
   */
  @Override
  public ByteBuffer encode(Packet packet, GroupContext groupContext, ChannelContext
          channelContext) {
    ShowPacket showPacket = (ShowPacket) packet;
    byte[] bytes = showPacket.getBody();
    int byteLen = 0;
    if(bytes != null) {
      byteLen = bytes.length;
    }

    //byteBuffer的总长度 = 消息头的长度 + 消息体的长度
    int allLen = Const.HEADER_LENGHT + byteLen;
    //创建一个新的bytebuffer
    ByteBuffer byteBuffer = ByteBuffer.allocate(allLen);
    //设置字节序
    byteBuffer.order(groupContext.getByteOrder());
    //写入消息类型
    byteBuffer.put(showPacket.getType());
    //写入消息头：消息内容的长度
    byteBuffer.putInt(byteLen);
    //写入消息体
    if(bytes != null) {
      byteBuffer.put(bytes);
    }
    return byteBuffer;
  }
}
