package cn.luoxi.show.common;

/**
 * 消息类型
 *
 * @author 夏智峰
 * @create 2017-10-30 10:38
 */
public interface Type {
  /**
   * 登录消息请求
   */
  byte LOGIN_REQ = 1;
  /**
   * 登录消息响应
   */
  byte LOGIN_RESP = 2;

  /**
   * 进入群组消息请求
   */
  byte JOIN_GROUP_REQ = 3;
  /**
   * 进入群组消息响应
   */
  byte JOIN_GROUP_RESP = 4;

  /**
   * 点对点消息请求
   */
  byte P2P_REQ = 5;
  /**
   * 点对点消息响应
   */
  byte P2P_RESP = 6;

  /**
   * 群聊消息请求
   */
  byte GROUP_MSG_REQ = 7;
  /**
   * 群聊消息响应
   */
  byte GROUP_MSG_RESP = 8;

  /**
   * 退群请求
   */
  byte RETREAT_GROUP_REQ = 9;

  /**
   * 退群相应
   */
  byte RETREAT_GROUP_RESP = 10;

  /**
   * 踢出登录请求
   */
  byte OFF_LINE_REQ = 11;

  /**
   * 踢出登录响应
   */
  byte OFF_LINT_RESP = 12;

  /**
   * 退出登录请求
   */
  byte UN_BIND_USER_REQ = 13;

  /**
   * 退出登录响应
   */
  byte UN_BIND_USER_RESP = 14;

  /**
   * 心跳
   */
  byte HEART_BEAT_REQ = 99;
}
