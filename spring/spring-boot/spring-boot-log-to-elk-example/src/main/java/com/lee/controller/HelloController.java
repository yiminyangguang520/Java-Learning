package com.lee.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author litz-a
 */
@Log4j2
@RestController
public class HelloController {

  @RequestMapping("/home")
  public String home() {
    log.error("Hi ! We have an Error. Hello World");
    return "Hello World ----spring-boot-log4j2";
  }

  @RequestMapping("/log")
  public String log() {
    log.error("{\"msg\":\"出现一个异常错误：请求连接失败\",\"level\":\"ERROR\",\"createTime\":\"2018-5-21 20:22:22\",\"provider\":\"xishuai\",\"ip\":\"192.168.1.11\",\"stackTrace\":\"java.lang.Exception\\n\\tat com.example.log_demo.LogDemoTests.logCustomField(LogDemoTests.java:33)\\n\\tat org.springframework.test.context.junit4.SpringJUnit4ClassRunner.run(SpringJUnit4ClassRunner.java:191)\\n\\tat org.junit.runner.JUnitCore.run(JUnitCore.java:137)\\n\\tat com.intellij.junit4.JUnit4IdeaTestRunner.startRunnerWithArgs(JUnit4IdeaTestRunner.java:68)\\n\\tat com.intellij.rt.execution.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:47)\\n\\tat com.intellij.rt.execution.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:242)\\n\\tat com.intellij.rt.execution.junit.JUnitStarter.main(JUnitStarter.java:70)\\n\",\"tag\":\"\",\"url\":\"\"}");
    return "Hello World ----spring-boot-log4j2";
  }
}