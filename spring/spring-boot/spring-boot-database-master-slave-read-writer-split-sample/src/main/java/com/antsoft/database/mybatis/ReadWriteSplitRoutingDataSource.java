package com.antsoft.database.mybatis;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 *
 * @author Jason
 * @date 2017/3/6
 */
public class ReadWriteSplitRoutingDataSource extends AbstractRoutingDataSource {

  @Override
  protected Object determineCurrentLookupKey() {
    return DbContextHolder.getDbType();
  }
}
