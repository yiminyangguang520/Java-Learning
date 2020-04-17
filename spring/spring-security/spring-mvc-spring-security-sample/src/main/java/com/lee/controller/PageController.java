package com.lee.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author min
 */
@Controller
public class PageController {

  /**
   * 权限控制相关页面
   * @return
   */
  @RequestMapping("/login.html")
  public String loginPage() {
    return "login";
  }

  @RequestMapping("/logout.html")
  public String logout() {
    return "logout";
  }

  @RequestMapping("/denied.html")
  public String deniedPage() {
    return "denied";
  }

  /**
   * 展示功能相关页面
   * @return
   */
  @RequestMapping("/index.html")
  public String indexPage() {
    return "index";
  }

  @RequestMapping("/student.html")
  public String studentPage() {
    return "student";
  }

  @RequestMapping("/student/detail.html")
  public String studentDetailPage() {
    return "student_detail";
  }

  @RequestMapping("/teacher.html")
  public String teacherPage() {
    return "teacher";
  }

  @RequestMapping("/class.html")
  public String classPage() {
    return "class";
  }

  @RequestMapping("/notice.html")
  public String noticePage() {
    return "notice";
  }

}
