package com.antsoft.database.mybatis;

/**
 *
 * @author Jason
 * @date 2017/3/6
 */
public class DbContextHolder {

  private static final ThreadLocal<DbType> contextHolder = new ThreadLocal<>();

  public static DbType getDbType() {
    return contextHolder.get() == null ? DbType.MASTER : contextHolder.get();
  }

  public static void setDbType(DbType dbType) {
    if (dbType == null) {
      throw new NullPointerException();
    }
    contextHolder.set(dbType);
  }

  public static void clearDbType() {
    contextHolder.remove();
  }

  public enum DbType {
    MASTER, SLAVE
  }

}
