package com.lee.async.service;

import com.lee.async.model.SearchResult;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author bruce
 * @date 2019/10/22
 * @description 异步搜索支持
 */
@Slf4j
@Service
public class AsyncService {

  @Autowired
  private final Map<String, ISearchService> searchServiceMap = new ConcurrentHashMap<>();

  public AsyncService(Map<String, ISearchService> searchServiceMap) {
    this.searchServiceMap.clear();
    searchServiceMap.forEach((k, v) -> this.searchServiceMap.put(k, v));
  }

  /**
   * 异步查询
   *
   * @param type 采用何种查询类型
   * @param text 查询内容
   * @return 将结果放入future中可采用异步回调获取
   */
  @Async
  public CompletableFuture<SearchResult> search(String type, String text) {
    SearchResult result = searchServiceMap.get(type).search(text);
    return CompletableFuture.completedFuture(result);
  }

}
