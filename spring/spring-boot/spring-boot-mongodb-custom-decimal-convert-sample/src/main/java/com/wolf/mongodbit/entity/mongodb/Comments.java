package com.wolf.mongodbit.entity.mongodb;

import java.math.BigDecimal;
import lombok.Data;

/**
 * @author litz-a
 */
@Data
public class Comments {

  private String cause;
  private String desc;
  private BigDecimal money;
}
