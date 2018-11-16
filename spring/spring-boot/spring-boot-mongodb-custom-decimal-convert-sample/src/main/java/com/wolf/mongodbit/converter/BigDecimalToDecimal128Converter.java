package com.wolf.mongodbit.converter;

import java.math.BigDecimal;
import org.bson.types.Decimal128;
import org.springframework.core.convert.converter.Converter;

/**
 * 自定义类型转换器BigDecimalToDecimal128
 * @author litz-a
 */
public class BigDecimalToDecimal128Converter implements Converter<BigDecimal, Decimal128> {

  @Override
  public Decimal128 convert(BigDecimal bigDecimal) {
    return new Decimal128(bigDecimal);
  }

}
