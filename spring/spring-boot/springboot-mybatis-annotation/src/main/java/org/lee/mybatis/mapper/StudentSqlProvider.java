package org.lee.mybatis.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;
import org.lee.mybatis.model.Student;
import org.lee.mybatis.utils.CoreSQL;

/**
 * @author bruce
 */
public class StudentSqlProvider {

  public String update(Student student) {
    return new SQL() {
      {
        UPDATE("student");

        if (student.getName() != null) {
          SET("name = #{name,jdbcType=VARCHAR}");
        }

        if (student.getGrade() != null) {
          SET("grade = #{grade,jdbcType=VARCHAR}");
        }

        if (student.getClassNumber() != null) {
          SET("class_number = #{classNumber,jdbcType=VARCHAR}");
        }

        if (student.getInstituteId() != null) {
          SET("institute_id = #{instituteId,jdbcType=INTEGER}");
        }

        WHERE("id = #{id,jdbcType=INTEGER}");
      }
    }.toString();
  }

  public String saveBatch(Map<String, Object> paramMap) {
    String result = new CoreSQL() {
      {
        List<Student> students = (List<Student>) paramMap.get("students");

        INSERT_INTO("student");

        INTO_COLUMNS("name", "grade", "class_number", "institute_id");

        List<List<String>> lists = new ArrayList<>();
        for (int i = 0; i < students.size(); i++) {
          List<String> columns = new ArrayList<>();
          columns.add("#{students[" + i + "].name,jdbcType=VARCHAR}");
          columns.add("#{students[" + i + "].grade,jdbcType=VARCHAR}");
          columns.add("#{students[" + i + "].classNumber,jdbcType=VARCHAR}");
          columns.add("#{students[" + i + "].instituteId,jdbcType=INTEGER}");
          lists.add(columns);
        }

        INTO_MULTI_VALUES(lists);
      }
    }.toString();

    return result;
  }
}
