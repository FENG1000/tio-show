package cn.luoxi.show.common.packets;

/**
 * 退群请求
 *
 * @author 夏智峰
 * @create 2017-10-31 9:40
 */
public class RetreatGroupReqBody extends BaseBody {
  private String groupName;

  public String getGroupName() {
    return groupName;
  }

  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }
}
