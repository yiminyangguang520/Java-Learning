package com.lee.mybatis.mapper;

import com.lee.mybatis.entity.Course;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author bruce
 */
public class CourseSqlProvider {

  public String update(final Course course) {
    return new SQL() {
      {
        UPDATE("course");

        if (course.getName() != null) {
          SET("name = #{name,jdbcType=VARCHAR}");
        }

        if (course.getLessonPeriod() != null) {
          SET("lesson_period = #{lessonPeriod,jdbcType=DOUBLE}");
        }

        if (course.getScore() != null) {
          SET("score = #{score,jdbcType=DOUBLE}");
        }

        WHERE("id = #{id,jdbcType=INTEGER}");
      }
    }.toString();
  }

}
