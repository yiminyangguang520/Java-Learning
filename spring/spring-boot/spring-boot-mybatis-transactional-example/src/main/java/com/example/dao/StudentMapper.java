package com.example.dao;

import com.example.entity.Student;
import com.example.entity.StudentExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.context.annotation.Lazy;

/**
 * @author litz-a
 */
public interface StudentMapper {

  long countByExample(StudentExample example);

  int deleteByExample(StudentExample example);

  @Delete({
      "delete from student",
      "where id = #{id,jdbcType=INTEGER}"
  })
  int deleteByPrimaryKey(Integer id);

  @Insert({
      "insert into student (id, name, ",
      "sex)",
      "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
      "#{sex,jdbcType=VARCHAR})"
  })
  int insert(Student record);

  int insertSelective(Student record);

  List<Student> selectByExample(StudentExample example);

  @Select({
      "select",
      "student.id as student_id, student.name as student_name, student.sex as student_sex",
      "from student student",
      "where student.id = #{id,jdbcType=INTEGER}"
  })
  @ResultMap("com.example.dao.StudentMapper.BaseResultMap")
  Student selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") Student record, @Param("example") StudentExample example);

  int updateByExample(@Param("record") Student record, @Param("example") StudentExample example);

  int updateByPrimaryKeySelective(Student record);

  @Update({
      "update student",
      "set name = #{name,jdbcType=VARCHAR},",
      "sex = #{sex,jdbcType=VARCHAR}",
      "where id = #{id,jdbcType=INTEGER}"
  })
  int updateByPrimaryKey(Student record);
}