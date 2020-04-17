package com.wolf.mongodbit.entity.mongodb;

import java.math.BigDecimal;
import lombok.Data;

/**
 * @author min
 */
@Data
public class Comments {

  private String cause;
  private String desc;
  private BigDecimal money;
}
