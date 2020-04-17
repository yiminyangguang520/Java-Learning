package com.us.example.dao;

import com.us.example.model.Role;
import com.us.example.model.RoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author min
 */
public interface RoleMapper {

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table sys_role
   *
   * @mbg.generated Fri Aug 24 08:56:53 CST 2018
   */
  long countByExample(RoleExample example);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table sys_role
   *
   * @mbg.generated Fri Aug 24 08:56:53 CST 2018
   */
  int deleteByExample(RoleExample example);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table sys_role
   *
   * @mbg.generated Fri Aug 24 08:56:53 CST 2018
   */
  @Delete({
      "delete from sys_role",
      "where id = #{id,jdbcType=INTEGER}"
  })
  int deleteByPrimaryKey(Integer id);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table sys_role
   *
   * @mbg.generated Fri Aug 24 08:56:53 CST 2018
   */
  @Insert({
      "insert into sys_role (id, name)",
      "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR})"
  })
  int insert(Role record);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table sys_role
   *
   * @mbg.generated Fri Aug 24 08:56:53 CST 2018
   */
  int insertSelective(Role record);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table sys_role
   *
   * @mbg.generated Fri Aug 24 08:56:53 CST 2018
   */
  List<Role> selectByExample(RoleExample example);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table sys_role
   *
   * @mbg.generated Fri Aug 24 08:56:53 CST 2018
   */
  @Select({
      "select",
      "role.id as role_id, role.name as role_name",
      "from sys_role role",
      "where role.id = #{id,jdbcType=INTEGER}"
  })
  @ResultMap("com.us.example.dao.RoleMapper.BaseResultMap")
  Role selectByPrimaryKey(Integer id);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table sys_role
   *
   * @mbg.generated Fri Aug 24 08:56:53 CST 2018
   */
  int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table sys_role
   *
   * @mbg.generated Fri Aug 24 08:56:53 CST 2018
   */
  int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table sys_role
   *
   * @mbg.generated Fri Aug 24 08:56:53 CST 2018
   */
  int updateByPrimaryKeySelective(Role record);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table sys_role
   *
   * @mbg.generated Fri Aug 24 08:56:53 CST 2018
   */
  @Update({
      "update sys_role",
      "set name = #{name,jdbcType=VARCHAR}",
      "where id = #{id,jdbcType=INTEGER}"
  })
  int updateByPrimaryKey(Role record);
}