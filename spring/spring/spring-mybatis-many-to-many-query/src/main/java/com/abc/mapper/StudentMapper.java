package com.abc.mapper;

import org.springframework.stereotype.Repository;
import com.abc.domain.Student;

//@Repository指定映射器名称为myStudentMapper
//相关内容，可参考笔者博客：
//http://legend2011.blog.51cto.com/3018495/980150
//视频公开课地址：http://bbs.51cto.com/open/do/course/cid/65
@Repository("myStudentMapper")
public interface StudentMapper {

  //根据id查找学生
  Student getById(int id);

  //添加一名学生
  void add(Student student);

  //修改学生信息
  void update(Student student);

  //删除学生信息
  void delete(int id);
}
