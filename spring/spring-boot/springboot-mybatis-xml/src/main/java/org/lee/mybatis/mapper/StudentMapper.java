package org.lee.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.lee.mybatis.model.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudentMapper {

    Student findById(Integer id);

    List<Student> findByIds(List<Integer> list);

    List<Student> findAll();

    int deleteById(Integer id);

    int deleteByIds(List<Integer> list);

    int save(Student record);

    int saveBatch(List<Student> list);

    int update(Student record);

    int updateBatch(List<Student> list);
}
