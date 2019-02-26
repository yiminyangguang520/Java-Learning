package cn.lqdev.learning.springboot.chapter35.config;

import cn.lqdev.learning.springboot.chapter35.biz.entity.StatusEnum;
import org.apache.ibatis.type.EnumOrdinalTypeHandler;
import org.apache.ibatis.type.MappedTypes;

/**
 * 枚举索引处理类
 * @author litz-a
 */
@MappedTypes(value = {StatusEnum.class})
public class CustomEnumOrdinalTypeHandler<E extends Enum<E>> extends EnumOrdinalTypeHandler<E> {

  public CustomEnumOrdinalTypeHandler(Class<E> type) {
    super(type);
  }
}
