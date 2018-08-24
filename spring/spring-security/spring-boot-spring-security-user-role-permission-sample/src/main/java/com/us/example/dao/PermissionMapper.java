package com.us.example.dao;

import com.us.example.model.Permission;
import com.us.example.model.PermissionExample;
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
public interface PermissionMapper {

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table sys_permission
   *
   * @mbg.generated Fri Aug 24 08:56:53 CST 2018
   */
  long countByExample(PermissionExample example);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table sys_permission
   *
   * @mbg.generated Fri Aug 24 08:56:53 CST 2018
   */
  int deleteByExample(PermissionExample example);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table sys_permission
   *
   * @mbg.generated Fri Aug 24 08:56:53 CST 2018
   */
  @Delete({
      "delete from sys_permission",
      "where id = #{id,jdbcType=INTEGER}"
  })
  int deleteByPrimaryKey(Integer id);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table sys_permission
   *
   * @mbg.generated Fri Aug 24 08:56:53 CST 2018
   */
  @Insert({
      "insert into sys_permission (id, name, ",
      "descritpion, url, ",
      "pid)",
      "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
      "#{descritpion,jdbcType=VARCHAR}, #{url,jdbcType=VARCHAR}, ",
      "#{pid,jdbcType=INTEGER})"
  })
  int insert(Permission record);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table sys_permission
   *
   * @mbg.generated Fri Aug 24 08:56:53 CST 2018
   */
  int insertSelective(Permission record);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table sys_permission
   *
   * @mbg.generated Fri Aug 24 08:56:53 CST 2018
   */
  List<Permission> selectByExample(PermissionExample example);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table sys_permission
   *
   * @mbg.generated Fri Aug 24 08:56:53 CST 2018
   */
  @Select({
      "select",
      "permission.id as permission_id, permission.name as permission_name, permission.descritpion as permission_descritpion, ",
      "permission.url as permission_url, permission.pid as permission_pid",
      "from sys_permission permission",
      "where permission.id = #{id,jdbcType=INTEGER}"
  })
  @ResultMap("com.us.example.dao.PermissionMapper.BaseResultMap")
  Permission selectByPrimaryKey(Integer id);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table sys_permission
   *
   * @mbg.generated Fri Aug 24 08:56:53 CST 2018
   */
  int updateByExampleSelective(@Param("record") Permission record, @Param("example") PermissionExample example);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table sys_permission
   *
   * @mbg.generated Fri Aug 24 08:56:53 CST 2018
   */
  int updateByExample(@Param("record") Permission record, @Param("example") PermissionExample example);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table sys_permission
   *
   * @mbg.generated Fri Aug 24 08:56:53 CST 2018
   */
  int updateByPrimaryKeySelective(Permission record);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table sys_permission
   *
   * @mbg.generated Fri Aug 24 08:56:53 CST 2018
   */
  @Update({
      "update sys_permission",
      "set name = #{name,jdbcType=VARCHAR},",
      "descritpion = #{descritpion,jdbcType=VARCHAR},",
      "url = #{url,jdbcType=VARCHAR},",
      "pid = #{pid,jdbcType=INTEGER}",
      "where id = #{id,jdbcType=INTEGER}"
  })
  int updateByPrimaryKey(Permission record);


  /**
   * findAll
   */
  List<Permission> findAll();

  /**
   * findByAdminUserId
   */
  List<Permission> findByAdminUserId(int userId);
}