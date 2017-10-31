package cn.luoxi.show.common.packets;

/**
 * 退群响应
 *
 * @author 夏智峰
 * @create 2017-10-31 9:41
 */
public class RetreatGroupRespBody extends BaseBody {
  private String groupName;
  private String msg;
  private Integer code;

  public String getGroupName() {
    return groupName;
  }

  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }
}
