package com.biostime.demo.service;

import com.biostime.demo.domain.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Function:
 * <P> 版权所有 ©2013 Biostime Inc.. All Rights Reserved
 * <p> 未经本公司许可，不得以任何方式复制或使用本程序任何部分 <p>
 * User: 12360
 * Date: 2016/9/7
 * Time: 18:36
 */
public interface LogService {
    List<Log> findByMethod(String model);

    List<Log> findByIdIn(List<Long> ids);

    List<Log> findByCode(String code);

    Page<Log> findPage(Pageable pageable);

    Log findById(Long id);
}
