package com.lee.example.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 格式要求 https://github.com/wenzhixin/bootstrap-table-examples/blob/master/json/data2.json
 * @author min
 */
public class PageBean<T> implements Serializable {

  private long total;

  private List<T> rows;

  public long getTotal() {
    return total;
  }

  public void setTotal(long total) {
    this.total = total;
  }

  public List<T> getRows() {
    return rows;
  }

  public void setRows(List<T> rows) {
    this.rows = rows;
  }
}
