package org.shiro.security.datasources;

import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


/**
 * @author yuanpb
 * @version 创建时间：2018年4月25日 上午10:50:40
 * 类说明：动态数据源
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

  private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

  public DynamicDataSource(DataSource defaultTargetDataSource, Map<Object, Object> targetDataSources) {
    super.setDefaultTargetDataSource(defaultTargetDataSource);
    super.setTargetDataSources(targetDataSources);
    super.afterPropertiesSet();
  }

  @Override
  protected Object determineCurrentLookupKey() {
    return getDataSource();
  }

  public static void setDataSource(String dataSource) {
    contextHolder.set(dataSource);
  }

  public static String getDataSource() {
    return contextHolder.get();
  }

  public static void clearDataSource() {
    contextHolder.remove();
  }

}
