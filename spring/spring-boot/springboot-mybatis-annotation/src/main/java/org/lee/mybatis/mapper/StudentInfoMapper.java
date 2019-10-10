package org.lee.mybatis.mapper;

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
import org.lee.mybatis.model.StudentInfo;
import org.springframework.stereotype.Repository;

/**
 * @author bruce
 */
@Mapper
@Repository
public interface StudentInfoMapper {

  @Delete({
    "delete from student_info",
    "where id = #{id,jdbcType=INTEGER}"
  })
  int deleteById(Integer id);

  @Insert({
    "insert into student_info (age, ",
    "address, email, ",
    "student_id)",
    "values (#{age,jdbcType=INTEGER}, ",
    "#{address,jdbcType=VARCHAR}, #{email,jdbcType=VARCHAR}, ",
    "#{studentId,jdbcType=INTEGER})"
  })
  @SelectKey(statement = "select last_insert_id()", keyProperty = "id", before = false, resultType = int.class)
  int save(StudentInfo studentInfo);


  @Select({
    "select",
    "id, age, address, email, student_id",
    "from student_info",
    "where id = #{id,jdbcType=INTEGER}"
  })
  @Results({
    @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
    @Result(column = "age", property = "age", jdbcType = JdbcType.INTEGER),
    @Result(column = "address", property = "address", jdbcType = JdbcType.VARCHAR),
    @Result(column = "email", property = "email", jdbcType = JdbcType.VARCHAR),
    @Result(column = "student_id", property = "studentId", jdbcType = JdbcType.INTEGER)
  })
  StudentInfo findById(Integer id);


  @UpdateProvider(type = StudentInfoSqlProvider.class, method = "update")
  int update(StudentInfo studentInfo);

  @Select({
    "select",
    "id, age, address, email, student_id",
    "from student_info"
  })
  @Results({
    @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
    @Result(column = "age", property = "age", jdbcType = JdbcType.INTEGER),
    @Result(column = "address", property = "address", jdbcType = JdbcType.VARCHAR),
    @Result(column = "email", property = "email", jdbcType = JdbcType.VARCHAR),
    @Result(column = "student_id", property = "studentId", jdbcType = JdbcType.INTEGER)
  })
  List<StudentInfo> findAll();
}
