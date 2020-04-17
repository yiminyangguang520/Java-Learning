package com.example.lee.service;


import com.example.lee.entity.Crash;

/**
 * @author min
 */
public interface CrashService {

  /**
   * 根据ID查询文档
   * @param id ID
   * @return
   */
  Crash findOneByDumpId(String id);

  String findById(String id);

}
