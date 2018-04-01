package com.example.lee;

import com.example.lee.entity.Crash;
import com.example.lee.service.CrashService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

  @Autowired
  private CrashService crashService;

  @Test
  public void contextLoads() {
    Crash crash = crashService.findOneByDumpId("4dca4838-7653-4392-9d84-bb4c6174dc8a");
    String crashStr = crashService.findById("4dca4838-7653-4392-9d84-bb4c6174dc8a");
    System.out.println(crash);
    System.out.println(crashStr);
  }

}
