package org.lee.mybatis.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.lee.mybatis.model.Student;
import org.springframework.stereotype.Repository;

/**
 * @author bruce
 */
@Mapper
@Repository
public interface StudentMapper {

  @Delete({
    "delete from student",
    "where id = #{id,jdbcType=INTEGER}"
  })
  int deleteById(Integer id);

  @Insert({
    "insert into student (id, name, ",
    "grade, class_number, ",
    "institute_id)",
    "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
    "#{grade,jdbcType=VARCHAR}, #{classNumber,jdbcType=VARCHAR}, ",
    "#{instituteId,jdbcType=INTEGER})"
  })
  int save(Student record);

  @Select({
    "select",
    "id, name, grade, class_number, institute_id",
    "from student",
    "where id = #{id,jdbcType=INTEGER}"
  })
  @ResultMap("org.lee.mybatis.mapper.StudentMapper.BaseResultMap")
  Student findById(Integer id);

  int update(Student record);

  List<Student> findAll();

  List<Student> findByIds(List<Integer> list);

  int deleteByIds(List<Integer> list);

  int saveBatch(List<Student> list);

  int updateBatch(List<Student> list);
}
