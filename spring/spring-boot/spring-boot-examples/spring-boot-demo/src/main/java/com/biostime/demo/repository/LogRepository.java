package com.biostime.demo.repository;


import com.biostime.demo.domain.Log;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Function:
 * <P> 版权所有 ©2013 Biostime Inc.. All Rights Reserved
 * <p> 未经本公司许可，不得以任何方式复制或使用本程序任何部分 <p>
 * User: 12360
 * Date: 2016/7/26
 * Time: 15:22
 */
public interface LogRepository extends JpaRepository<Log, Long> {

    List<Log> findByMethod(String model);

    List<Log> findByIdIn(List<Long> ids);

    List<Log> findByCode(String code);

    @Query(value = "SELECT log FROM Log log",countQuery = "SELECT count(*) FROM Log ")
    Page<Log> findPage(Pageable pageable);
}
