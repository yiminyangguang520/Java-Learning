package com.lee.mybatis.config;

/**
 * @author bruce
 */
public class DbContextHolder {

  public enum DbType {
    MASTER, SLAVE
  }

  private static ThreadLocal<DbType> contextHolder = new ThreadLocal<>();

  public static void setDbType(DbType dbType) {
    if (dbType == null) {
      throw new NullPointerException();
    }
    contextHolder.set(dbType);
  }

  public static DbType getDbType() {
    return contextHolder.get() == null ? DbType.MASTER : contextHolder.get();
  }

  public static void clearDbType() {
    contextHolder.remove();
  }

}
