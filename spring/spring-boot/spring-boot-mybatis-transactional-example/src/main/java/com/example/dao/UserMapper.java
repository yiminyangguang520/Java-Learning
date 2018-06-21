package com.example.dao;

import com.example.entity.User;
import com.example.entity.UserExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author litz-a
 */
public interface UserMapper {

  long countByExample(UserExample example);

  int deleteByExample(UserExample example);

  @Delete({
      "delete from user",
      "where id = #{id,jdbcType=INTEGER}"
  })
  int deleteByPrimaryKey(Integer id);

  @Insert({
      "insert into user (id, username, ",
      "password, user_sex, ",
      "nick_name)",
      "values (#{id,jdbcType=INTEGER}, #{username,jdbcType=VARCHAR}, ",
      "#{password,jdbcType=VARCHAR}, #{userSex,jdbcType=VARCHAR}, ",
      "#{nickName,jdbcType=VARCHAR})"
  })
  int insert(User record);

  int insertSelective(User record);

  List<User> selectByExample(UserExample example);

  @Select({
      "select",
      "user.id as user_id, user.username as user_username, user.password as user_password, ",
      "user.user_sex as user_user_sex, user.nick_name as user_nick_name",
      "from user user",
      "where user.id = #{id,jdbcType=INTEGER}"
  })
  @ResultMap("com.example.dao.UserMapper.BaseResultMap")
  User selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

  int updateByExample(@Param("record") User record, @Param("example") UserExample example);

  int updateByPrimaryKeySelective(User record);

  @Update({
      "update user",
      "set username = #{username,jdbcType=VARCHAR},",
      "password = #{password,jdbcType=VARCHAR},",
      "user_sex = #{userSex,jdbcType=VARCHAR},",
      "nick_name = #{nickName,jdbcType=VARCHAR}",
      "where id = #{id,jdbcType=INTEGER}"
  })
  int updateByPrimaryKey(User record);
}