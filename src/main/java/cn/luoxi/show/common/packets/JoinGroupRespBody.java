package cn.luoxi.show.common.packets;

/**
 * 进群响应
 *
 * @author 夏智峰
 * @create 2017-10-30 15:13
 */
public class JoinGroupRespBody extends BaseBody {
  public static interface Code {
    Integer SUCCESS = 1;
    Integer FAIL = 2;
  }
  private Integer code;
  /**
   * 进群失败需要说明原因
   */
  private String msg;
  private String groupName;

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public String getGroupName() {
    return groupName;
  }

  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }
}
