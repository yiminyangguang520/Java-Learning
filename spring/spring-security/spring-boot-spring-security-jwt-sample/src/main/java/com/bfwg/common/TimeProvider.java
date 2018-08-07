package com.bfwg.common;

import java.io.Serializable;
import java.util.Date;
import org.springframework.stereotype.Component;

/**
 *
 * @author fanjin
 * @date 2017-10-07
 */
@Component
public class TimeProvider implements Serializable {

  private static final long serialVersionUID = -3301695478208950415L;

  public Date now() {
    return new Date();
  }
}