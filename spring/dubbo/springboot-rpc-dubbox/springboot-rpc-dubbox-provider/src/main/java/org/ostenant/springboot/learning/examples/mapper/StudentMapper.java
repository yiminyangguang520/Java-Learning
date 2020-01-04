package org.ostenant.springboot.learning.examples.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.ostenant.springboot.learning.examples.entities.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudentMapper {

    @Delete({
            "delete from student",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteById(Integer id);

    @Insert({
            "insert into student (name, ",
            "grade, class_number, ",
            "institute_id)",
            "values (#{name,jdbcType=VARCHAR}, ",
            "#{grade,jdbcType=VARCHAR}, #{classNumber,jdbcType=VARCHAR}, ",
            "#{instituteId,jdbcType=INTEGER})"
    })
    @SelectKey(statement = "select last_insert_id()", keyProperty = "id", before = false, resultType = int.class)
    int save(Student student);


    @Select({
            "select",
            "id, name, grade, class_number, institute_id",
            "from student",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "grade", property = "grade", jdbcType = JdbcType.VARCHAR),
            @Result(column = "class_number", property = "classNumber", jdbcType = JdbcType.VARCHAR),
            @Result(column = "institute_id", property = "instituteId", jdbcType = JdbcType.INTEGER)
    })
    Student findById(Integer id);


    @Select({
            "select",
            "id, name, grade, class_number, institute_id",
            "from student"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "grade", property = "grade", jdbcType = JdbcType.VARCHAR),
            @Result(column = "class_number", property = "classNumber", jdbcType = JdbcType.VARCHAR),
            @Result(column = "institute_id", property = "instituteId", jdbcType = JdbcType.INTEGER)
    })
    List<Student> findAll();


    @UpdateProvider(type = StudentSqlProvider.class, method = "update")
    int update(Student record);

    @InsertProvider(type = StudentSqlProvider.class, method = "saveBatch")
    @SelectKey(statement = "select last_insert_id()", keyProperty = "id", before = false, resultType = int.class)
//    @Options(useGeneratedKeys = true, keyProperty = "id")
    int saveBatch(@Param("students") List<Student> students);
}