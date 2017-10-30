package cn.luoxi.show.common.packets;

/**
 * 公用字段
 * @author 夏智峰
 * @create 2017-10-30 10:39
 */
public class BaseBody {
  private Long time = System.currentTimeMillis();

  public Long getTime() {
    return time;
  }

  public void setTime(Long time) {
    this.time = time;
  }
}
