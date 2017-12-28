package com.abc.mapper;

import com.abc.domain.Teacher;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherMapper {

  Teacher getById(int id);

}
