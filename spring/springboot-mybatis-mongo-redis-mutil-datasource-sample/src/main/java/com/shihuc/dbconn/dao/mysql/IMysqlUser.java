package com.shihuc.dbconn.dao.mysql;

import com.shihuc.dbconn.pojo.mysql.MysqlUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface IMysqlUser {

  /**
   * 根据用户ID获取用户
   * @param userId
   * @return
   */
  @Select("SELECT * FROM user WHERE id = #{userId}")
  MysqlUser getUser(int userId);

  /**
   * 插入用户
   * @param user
   * @return
   */
  @Insert("insert into user (username, job, age, hometown) values(#{username}, #{job}, #{age}, #{hometown})")
  int addUser(MysqlUser user);
}
