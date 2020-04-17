package cn.lqdev.learning.springboot.chapter35.biz.mapper;

import cn.lqdev.learning.springboot.chapter35.biz.entity.User;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * xml映射
 *
 * @author min
 */
public interface UserXmlMapper {

  User queryOne(Long id);

  int insert(User user);

  void update(User user);

  void delete(Long id);

  List<User> queryByParams(@Param("code") String code);
}
