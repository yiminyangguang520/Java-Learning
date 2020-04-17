package com.lee.validation.constants;

import com.lee.validation.core.validator.IntArrayValuable;
import java.util.Arrays;

/**
 * @author min
 */

public enum GenderEnum implements IntArrayValuable {

  MALE(1, "男"),
  FEMALE(2, "女");

  /**
   * 值数组
   */
  public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(GenderEnum::getValue).toArray();

  /**
   * 性别值
   */
  private final Integer value;
  /**
   * 性别名
   */
  private final String name;

  GenderEnum(Integer value, String name) {
    this.value = value;
    this.name = name;
  }

  public Integer getValue() {
    return value;
  }

  public String getName() {
    return name;
  }

  @Override
  public int[] array() {
    return ARRAYS;
  }

}
