package org.shiro.security.common.utils;

/**
 * @author yuanpb
 * @version 创建时间：2018年4月25日 上午10:47:28
 * 类说明：常量
 */
public class Constant {

  /**
   * 超级管理员ID
   */
  public static final int SUPER_ADMIN = 1;
  /**
   * 数据权限过滤
   */
  public static final String SQL_FILTER = "sql_filter";
  /**
   * 没有权限，请联系管理员授权
   */
  public static final String NOACCESS = "没有权限，请联系管理员授权";
  /**
   * 数据库中已存在该记录
   */
  public static final String NOWEXITS = "数据库中已存在该记录";
  /**
   * 用户未登陆，请登陆
   */
  public static final String NOLONIN = "用户未登陆，请登陆";
  /**
   * 请先删除子部门
   */
  public static final String DELETECHILE = "请先删除子部门";
  /**
   * 文件上传成功
   */
  public static final String FILESUCESS = "文件上传成功";
  /**
   * 文件上传失败
   */
  public static final String FILEFAILE = "文件上传失败";
  /**
   * 文件为空
   */
  public static final String FILEEMP = "文件为空";


  /**
   * 菜单类型
   */
  public enum MenuType {
    /**
     * 目录
     */
    CATALOG(0),
    /**
     * 菜单
     */
    MENU(1),
    /**
     * 按钮
     */
    BUTTON(2);

    private int value;

    MenuType(int value) {
      this.value = value;
    }

    public int getValue() {
      return value;
    }
  }

  /**
   * 定时任务状态
   */
  public enum ScheduleStatus {
    /**
     * 正常
     */
    NORMAL(0),
    /**
     * 暂停
     */
    PAUSE(1);

    private int value;

    ScheduleStatus(int value) {
      this.value = value;
    }

    public int getValue() {
      return value;
    }
  }

  /**
   * 云服务商
   */
  public enum CloudService {
    /**
     * 七牛云
     */
    QINIU(1),
    /**
     * 阿里云
     */
    ALIYUN(2),
    /**
     * 腾讯云
     */
    QCLOUD(3);

    private int value;

    CloudService(int value) {
      this.value = value;
    }

    public int getValue() {
      return value;
    }
  }

}
