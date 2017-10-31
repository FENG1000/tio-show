package cn.luoxi.show.common.packets;

/**
 * 踢掉其他设备登录请求
 *
 * @author 夏智峰
 * @create 2017-10-31 13:07
 */
public class OffLineReqBody extends BaseBody {
  /**
   * 将被踢下线的用户id
   */
  private String userId;

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }
}
