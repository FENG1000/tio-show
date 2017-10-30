package cn.luoxi.show.common.packets;

/**
 * 进群请求
 *
 * @author 夏智峰
 * @create 2017-10-30 15:12
 */
public class JoinGroupReqBody extends BaseBody {
  /**
   * 只需要通过群名称就可以申请加入群
   */
  private String groupName;

  public String getGroupName() {
    return groupName;
  }

  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }
}
