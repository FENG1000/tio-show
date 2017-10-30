package cn.luoxi.show.common.packets;

/**
 * 群聊消息响应
 *
 * @author 夏智峰
 * @create 2017-10-30 15:53
 */
public class GroupMsgRespBody extends BaseBody {
  private String fromName;
  private String toGroup;
  private String text;

  public String getFromName() {
    return fromName;
  }

  public void setFromName(String fromName) {
    this.fromName = fromName;
  }

  public String getToGroup() {
    return toGroup;
  }

  public void setToGroup(String toGroup) {
    this.toGroup = toGroup;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }
}
