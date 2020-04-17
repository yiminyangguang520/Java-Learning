package org.jdonee.cooking.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.jdonee.cooking.domain.UserInfo;
import org.springframework.stereotype.Repository;

/**
 * @author min
 */
@Repository
public interface UserMapper {

  /**
   * findByAccount
   * @param account
   * @return
   */
  @Select("select * from t_user where account = #{account}")
  UserInfo findByAccount(@Param("account") String account);
}
