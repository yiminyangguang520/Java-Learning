package org.lee.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.lee.mybatis.model.StudentInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudentInfoMapper {

    StudentInfo findById(Integer id);

    List<StudentInfo> findByIds(List<Integer> list);

    List<StudentInfo> findAll();

    int deleteById(Integer id);

    int deleteByIds(List<Integer> list);

    int save(StudentInfo record);

    int saveBatch(List<StudentInfo> list);

    int update(StudentInfo record);

    int updateBatch(List<StudentInfo> list);
}
