package cn.luoxi.show.common.packets;

/**
 * 点对点请求
 *
 * @author 夏智峰
 * @create 2017-10-30 13:53
 */
public class P2PReqBody extends BaseBody {
  private String text;

  private String toUserId;

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getToUserId() {
    return toUserId;
  }

  public void setToUserId(String toUserId) {
    this.toUserId = toUserId;
  }
}
