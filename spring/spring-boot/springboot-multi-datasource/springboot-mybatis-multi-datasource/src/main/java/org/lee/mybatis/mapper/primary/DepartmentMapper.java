package org.lee.mybatis.mapper.primary;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.lee.mybatis.model.primary.Department;
import org.lee.mybatis.model.primary.DepartmentExample;
import org.springframework.stereotype.Repository;

/**
 * @author bruce
 */
@Repository
public interface DepartmentMapper {

  @Select({
    "select",
    "id, name, description",
    "from department",
    "where id = #{id,jdbcType=VARCHAR}"
  })
  @ResultMap("org.lee.mybatis.mapper.primary.DepartmentMapper.BaseResultMap")
  Department selectByPrimaryKey(String id);

  long countByExample(DepartmentExample example);

  int deleteByExample(DepartmentExample example);

  @Insert({
    "insert into department (id, name, ",
    "description)",
    "values (#{id,jdbcType=VARCHAR}, #{name,jdbcType=VARCHAR}, ",
    "#{description,jdbcType=VARCHAR})"
  })
  int insert(Department record);

  @Delete({
    "delete from department",
    "where id = #{id,jdbcType=VARCHAR}"
  })
  int deleteByPrimaryKey(String id);

  int insertSelective(Department record);

  List<Department> selectByExample(DepartmentExample example);

  int updateByPrimaryKeySelective(Department record);

  int updateByExampleSelective(@Param("record") Department record, @Param("example") DepartmentExample example);

  int updateByExample(@Param("record") Department record, @Param("example") DepartmentExample example);
}
