package org.lee.mybatis.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
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
    "insert into student_info (id, age, ",
    "address, email, ",
    "student_id)",
    "values (#{id,jdbcType=INTEGER}, #{age,jdbcType=INTEGER}, ",
    "#{address,jdbcType=VARCHAR}, #{email,jdbcType=VARCHAR}, ",
    "#{studentId,jdbcType=INTEGER})"
  })
  int save(StudentInfo record);

  @Select({
    "select",
    "id, age, address, email, student_id",
    "from student_info",
    "where id = #{id,jdbcType=INTEGER}"
  })
  @ResultMap("org.lee.mybatis.mapper.StudentInfoMapper.BaseResultMap")
  StudentInfo findById(Integer id);

  int update(StudentInfo record);

  List<StudentInfo> findAll();

  List<StudentInfo> findByIds(List<Integer> list);

  int deleteByIds(List<Integer> list);

  int saveBatch(List<StudentInfo> list);

  int updateBatch(List<StudentInfo> list);
}
