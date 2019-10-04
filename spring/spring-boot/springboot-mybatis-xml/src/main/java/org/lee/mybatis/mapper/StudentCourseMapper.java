package org.lee.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.lee.mybatis.model.StudentCourse;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudentCourseMapper {

    StudentCourse findById(Integer id);

    List<StudentCourse> findByIds(List<Integer> list);

    List<StudentCourse> findAll();

    int deleteById(Integer id);

    int deleteByIds(List<Integer> list);

    int save(StudentCourse record);

    int saveBatch(List<StudentCourse> list);

    int update(StudentCourse record);

    int updateBatch(List<StudentCourse> list);
}
