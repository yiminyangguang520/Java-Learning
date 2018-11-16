package com.wolf.mongodbit.converter;

import java.math.BigDecimal;
import org.bson.types.Decimal128;
import org.springframework.core.convert.converter.Converter;

/**
 * 自定义的类型转换器Decimal128ToBigDecimal
 * @author litz-a
 */

public class Decimal128ToBigDecimalConverter implements Converter<Decimal128, BigDecimal> {

  @Override
  public BigDecimal convert(Decimal128 decimal128) {
    return decimal128.bigDecimalValue();
  }
}
