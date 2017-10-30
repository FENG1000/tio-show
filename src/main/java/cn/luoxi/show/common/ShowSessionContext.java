package cn.luoxi.show.common;

/**
 * 生产环境中模拟加session
 *
 * @author 夏智峰
 * @create 2017-10-30 10:46
 */
public class ShowSessionContext {
  private String token = null;
  private String userId = null;

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }
}
