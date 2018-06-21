package com.example.demo;

import com.example.service.StudentService;
import com.example.service.TeacherService;
import com.example.service.TransService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

  @Autowired
  private TeacherService teacherService;

  @Autowired
  private StudentService studentService;

  @Autowired
  private TransService transService;

  @Test
  public void contextLoads() {
    try {
      transService.operate1();
    } catch (Exception e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
    }
  }

}
