package com.biostime.demo.service.impl;

import com.biostime.demo.domain.Log;
import com.biostime.demo.repository.LogRepository;
import com.biostime.demo.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Function:
 * <P> 版权所有 ©2013 Biostime Inc.. All Rights Reserved
 * <p> 未经本公司许可，不得以任何方式复制或使用本程序任何部分 <p>
 * User: 12360
 * Date: 2016/9/7
 * Time: 18:37
 */
@Service
@Slf4j
public class LogServiceImpl implements LogService {

    @Autowired
    private LogRepository logRepository;

    @Override
    @Cacheable(value = "findByMethod")
    public List<Log> findByMethod(String model) {
        log.info("=================jdbc=================");
        return logRepository.findByMethod(model);
    }

    @Override
    @Cacheable(value = "findPage")
    public List<Log> findByIdIn(List<Long> ids) {
        return logRepository.findByIdIn(ids);
    }

    @Override
    public List<Log> findByCode(String code) {
        return logRepository.findByCode(code);
    }

    @Override
    @Cacheable(value = "findPage")
    public Page<Log> findPage(Pageable pageable) {
        return logRepository.findPage(pageable);
    }

    public Log findById(Long id){
        return logRepository.getOne(id);
    }
}
