package cn.luoxi.show.common.packets;

/**
 * 登陆响应
 *
 * @author 夏智峰
 * @create 2017-10-30 10:42
 */
public class LoginRespBody extends BaseBody {
  public static interface  Code {
    Integer SUCCESS = 1;
    Integer FAIL = 2;
  }

  private String token;
  private Integer code;

  private String msg;

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
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
