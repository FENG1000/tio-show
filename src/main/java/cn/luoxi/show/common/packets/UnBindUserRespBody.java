package cn.luoxi.show.common.packets;

/**
 * 退出登录响应、
 *
 * @author 夏智峰
 * @create 2017-10-31 13:51
 */
public class UnBindUserRespBody extends BaseBody {
  private String userId;
  /**
   * 退出登录消息内容
   */
  private String msg;
  private Integer code;

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
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
