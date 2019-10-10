package org.lee.mybatis.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.lee.mybatis.model.StudentCourse;
import org.springframework.stereotype.Repository;

/**
 * @author bruce
 */
@Mapper
@Repository
public interface StudentCourseMapper {

  @Delete({
    "delete from student_course",
    "where id = #{id,jdbcType=INTEGER}"
  })
  int deleteById(Integer id);

  @Insert({
    "insert into student_course (id, student_id, ",
    "course_id, score)",
    "values (#{id,jdbcType=INTEGER}, #{studentId,jdbcType=INTEGER}, ",
    "#{courseId,jdbcType=INTEGER}, #{score,jdbcType=DOUBLE})"
  })
  int save(StudentCourse record);

  @Select({
    "select",
    "id, student_id, course_id, score",
    "from student_course",
    "where id = #{id,jdbcType=INTEGER}"
  })
  @ResultMap("org.lee.mybatis.mapper.StudentCourseMapper.BaseResultMap")
  StudentCourse findById(Integer id);

  int update(StudentCourse record);

  List<StudentCourse> findAll();

  List<StudentCourse> findByIds(List<Integer> list);

  int deleteByIds(List<Integer> list);

  int saveBatch(List<StudentCourse> list);

  int updateBatch(List<StudentCourse> list);
}
