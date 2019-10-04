package org.lee.mybatis;

import com.github.pagehelper.PageHelper;
import org.lee.mybatis.page.mapper.InstituteMapper;
import org.lee.mybatis.page.model.Institute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

/**
 * @author bruce
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

    private InstituteMapper instituteMapper;

    @Autowired
    public void setInstituteMapper(InstituteMapper instituteMapper) {
        this.instituteMapper = instituteMapper;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        PageHelper.startPage(0, 5);
        List<Institute> institutes = instituteMapper.findAll();
        institutes.forEach(System.out::println);
    }

}
