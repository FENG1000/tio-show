package cn.luoxi.show.common.packets;

/**
 * 踢掉其他设备登录响应
 *
 * @author 夏智峰
 * @create 2017-10-31 13:10
 */
public class OffLineRespBody extends BaseBody {
  /**
   * 被踢掉的用户id
   */
  private String userId;
  /**
   * 是否成功
   */
  private Integer code;
  /**
   * 返回的消息
   */
  private String msg;

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

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
}
