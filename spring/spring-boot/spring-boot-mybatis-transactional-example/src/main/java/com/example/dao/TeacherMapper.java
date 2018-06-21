package com.example.dao;

import com.example.entity.Teacher;
import com.example.entity.TeacherExample;
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
public interface TeacherMapper {

  long countByExample(TeacherExample example);

  int deleteByExample(TeacherExample example);

  @Delete({
      "delete from teacher",
      "where id = #{id,jdbcType=INTEGER}"
  })
  int deleteByPrimaryKey(Integer id);

  @Insert({
      "insert into teacher (id, name, ",
      "sex, major)",
      "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
      "#{sex,jdbcType=VARCHAR}, #{major,jdbcType=VARCHAR})"
  })
  int insert(Teacher record);

  int insertSelective(Teacher record);

  List<Teacher> selectByExample(TeacherExample example);

  @Select({
      "select",
      "teacher.id as teacher_id, teacher.name as teacher_name, teacher.sex as teacher_sex, ",
      "teacher.major as teacher_major",
      "from teacher teacher",
      "where teacher.id = #{id,jdbcType=INTEGER}"
  })
  @ResultMap("com.example.dao.TeacherMapper.BaseResultMap")
  Teacher selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") Teacher record, @Param("example") TeacherExample example);

  int updateByExample(@Param("record") Teacher record, @Param("example") TeacherExample example);

  int updateByPrimaryKeySelective(Teacher record);

  @Update({
      "update teacher",
      "set name = #{name,jdbcType=VARCHAR},",
      "sex = #{sex,jdbcType=VARCHAR},",
      "major = #{major,jdbcType=VARCHAR}",
      "where id = #{id,jdbcType=INTEGER}"
  })
  int updateByPrimaryKey(Teacher record);
}