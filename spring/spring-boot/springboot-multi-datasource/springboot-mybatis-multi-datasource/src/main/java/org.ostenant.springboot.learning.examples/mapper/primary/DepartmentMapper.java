package org.ostenant.springboot.learning.examples.mapper.primary;

import org.apache.ibatis.annotations.*;
import org.ostenant.springboot.learning.examples.model.primary.Department;
import org.ostenant.springboot.learning.examples.model.primary.DepartmentExample;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentMapper {

    @Select({
            "select",
            "id, name, description",
            "from department",
            "where id = #{id,jdbcType=VARCHAR}"
    })
    @ResultMap("org.ostenant.springboot.learning.examples.mapper.primary.DepartmentMapper.BaseResultMap")
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