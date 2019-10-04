package org.lee.mybatis;

import com.github.pagehelper.PageHelper;
import org.lee.mybatis.mapper.InstituteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    private InstituteMapper instituteMapper;

    @Autowired
    public void setInstituteMapper(InstituteMapper instituteMapper) {
        this.instituteMapper = instituteMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        PageHelper.startPage(0, 5);
        instituteMapper.findAll().stream().forEach(System.out::println);
    }

}
