package cn.luoxi.show.common.packets;

/**
 * 点对点消息响应
 *
 * @author 夏智峰
 * @create 2017-10-30 13:55
 */
public class P2PRespBody extends BaseBody {
  private String text;
  private String fromUserId;

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getFromUserId() {
    return fromUserId;
  }

  public void setFromUserId(String fromUserId) {
    this.fromUserId = fromUserId;
  }
}
