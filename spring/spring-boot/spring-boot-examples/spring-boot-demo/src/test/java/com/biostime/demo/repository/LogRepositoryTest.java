package com.biostime.demo.repository;

import com.biostime.demo.common.CommonTest;
import com.biostime.demo.domain.Log;
import com.biostime.demo.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

/**
 * Function:data jpa
 * <P> 版权所有 ©2013 Biostime Inc.. All Rights Reserved
 * <p> 未经本公司许可，不得以任何方式复制或使用本程序任何部分 <p>
 * User: 12360
 * Date: 2016/7/26
 * Time: 16:42
 */
@Slf4j
public class LogRepositoryTest extends CommonTest {

    @Autowired
    private LogRepository logRepository;

    @Test
    public void testFindAll(){
        List<Log> roleList = logRepository.findAll();
        log.info("findAll :  " + JsonUtils.toString(roleList));
    }

    @Test
    public void testFindByIdIn(){
        List<Long> ids = new ArrayList<>();
        ids.add(4L);
        ids.add(5L);
        ids.add(6L);
        List<Log> logList = logRepository.findByIdIn(ids);
        log.info("findByIdIn :  " + JsonUtils.toString(logList));
    }

    @Test
    public void testFindByCode(){
        List<Log> roleList = logRepository.findByCode("100");
        log.info("findByCode :  " + JsonUtils.toString(roleList));
    }

    @Test
    public void testFindByMethod(){
        List<Log> roleList = logRepository.findByMethod("updateRole");
        log.info("findByMethod :  " + JsonUtils.toString(roleList));
    }

    @Test
    public void testFindPage(){
        Pageable pageable = new PageRequest(1,5);
        Page<Log> roleList = logRepository.findPage(pageable);
        log.info("findByMethod :  " + JsonUtils.toString(roleList));
    }
}
