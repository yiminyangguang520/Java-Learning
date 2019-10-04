package com.lee.mybatis.mapper;

import com.lee.mybatis.entity.Course;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

/**
 * @author bruce
 */
@Mapper
@Repository
public interface CourseMapper {

  @Delete({
    "delete from course",
    "where id = #{id,jdbcType=INTEGER}"
  })
  int deleteById(Integer id);


  @Insert({
    "insert into course (name, ",
    "lesson_period, score)",
    "values (#{name,jdbcType=VARCHAR}, ",
    "#{lessonPeriod,jdbcType=DOUBLE}, #{score,jdbcType=DOUBLE})"
  })
  @SelectKey(statement = "select last_insert_id()", keyProperty = "id", before = false, resultType = int.class)
  int save(Course course);


  @Select({
    "select",
    "id, name, lesson_period, score",
    "from course",
    "where id = #{id,jdbcType=INTEGER}"
  })
  @Results({
    @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
    @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
    @Result(column = "lesson_period", property = "lessonPeriod", jdbcType = JdbcType.DOUBLE),
    @Result(column = "score", property = "score", jdbcType = JdbcType.DOUBLE)
  })
  Course findById(Integer id);


  @UpdateProvider(type = CourseSqlProvider.class, method = "update")
  int update(Course course);

  @Select({
    "select",
    "id, name, lesson_period, score",
    "from course"
  })
  @Results({
    @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
    @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
    @Result(column = "lesson_period", property = "lessonPeriod", jdbcType = JdbcType.DOUBLE),
    @Result(column = "score", property = "score", jdbcType = JdbcType.DOUBLE)
  })
  List<Course> findAll();

  @Delete({
    "delete from course"
  })
  void deleteAll();
}
