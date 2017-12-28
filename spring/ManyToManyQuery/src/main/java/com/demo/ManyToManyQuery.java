package com.demo;

import java.util.List;

import org.springframework.context.ApplicationContext;

import com.abc.service.CourseService;
import com.abc.service.StudentService;
import com.abc.domain.Course;
import com.abc.domain.Student;
import com.abc.domain.Teacher;

import org.springframework.context.support.ClassPathXmlApplicationContext;


public class ManyToManyQuery {

  private static ApplicationContext ctx;

  static {
    //在类路径下寻找spring主配置文件，启动spring容器
    ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
  }


  public static void main(String[] args) {

    //查询课程及选修此课程的学生
    /*
    int i = 0, length = 0;
    List<Student> list = null;
    CourseService courseService = (CourseService) ctx.getBean("courseService");
    Course course = courseService.getById(1);
    //获取选修了此课程的学生
    list = course.getStudents();
    length = list.size();

    StringBuilder info = new StringBuilder("课程名称：");
    info.append(course.getCourseName());
    info.append("   选修此课程的学生姓名：");

    while (i < length) {
      info.append(list.get(i).getName());
      info.append("   ");
      i++;
    }

    System.out.println(info.toString());
    */

    //查询学生及该学生选修的课程
    int i = 0, length = 0;
    List<Course> list = null;
    StudentService studentService = (StudentService) ctx.getBean("studentService");
    Student student = studentService.getById(7);
    //获取该学生选修的课程
    list = student.getCourses();
    StringBuilder info = new StringBuilder("学生姓名：");

    info.append(student.getName());
    info.append("    ");

    length = list.size();
    info.append("所选课程名称：");

    while (i < length) {
      info.append(list.get(i).getCourseName());
      info.append("    ");
      i++;
    }

    System.out.println(info.toString());
  }

}