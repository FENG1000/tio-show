package cn.luoxi.show.common.packets;

/**
 * 群聊消息请求
 *
 * @author 夏智峰
 * @create 2017-10-30 15:49
 */
public class GroupMsgReqBody extends BaseBody {
  private String toGroupName;
  private String text;

  public String getToGroupName() {
    return toGroupName;
  }

  public void setToGroupName(String toGroupName) {
    this.toGroupName = toGroupName;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }
}
