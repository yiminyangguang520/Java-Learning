package org.lee.mybatis.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.lee.mybatis.model.Course;
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
    "insert into course (id, name, ",
    "lesson_period, score)",
    "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
    "#{lessonPeriod,jdbcType=DOUBLE}, #{score,jdbcType=DOUBLE})"
  })
  int save(Course record);

  @Select({
    "select",
    "id, name, lesson_period, score",
    "from course",
    "where id = #{id,jdbcType=INTEGER}"
  })
  @ResultMap("org.lee.mybatis.mapper.CourseMapper.BaseResultMap")
  Course findById(Integer id);

  int update(Course record);

  List<Course> findAll();

  List<Course> findByIds(List<Integer> list);

  int deleteByIds(List<Integer> list);

  int saveBatch(List<Course> list);

  int updateBatch(List<Course> list);
}
