package org.lee.mybatis.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
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
    "insert into student_course (student_id, ",
    "course_id, score)",
    "values (#{studentId,jdbcType=INTEGER}, ",
    "#{courseId,jdbcType=INTEGER}, #{score,jdbcType=DOUBLE})"
  })
  @SelectKey(statement = "select last_insert_id()", keyProperty = "id", before = false, resultType = int.class)
  int save(StudentCourse studentCourse);


  @Select({
    "select",
    "id, student_id, course_id, score",
    "from student_course",
    "where id = #{id,jdbcType=INTEGER}"
  })
  @Results({
    @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
    @Result(column = "student_id", property = "studentId", jdbcType = JdbcType.INTEGER),
    @Result(column = "course_id", property = "courseId", jdbcType = JdbcType.INTEGER),
    @Result(column = "score", property = "score", jdbcType = JdbcType.DOUBLE)
  })
  StudentCourse findById(Integer id);


  @UpdateProvider(type = StudentCourseSqlProvider.class, method = "update")
  int update(StudentCourse studentCourse);

}
