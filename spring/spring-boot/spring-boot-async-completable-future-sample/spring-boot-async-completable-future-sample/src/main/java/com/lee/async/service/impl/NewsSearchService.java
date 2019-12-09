package com.lee.async.service.impl;

import com.lee.async.model.SearchResult;
import com.lee.async.util.MathUtil;
import com.lee.async.util.TimeUtil;
import com.lee.async.service.ISearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author bruce
 * @date 2019/10/22
 * @description 新闻搜素
 */
@Slf4j
@Service("news")
public class NewsSearchService implements ISearchService {

  @Override
  public SearchResult search(String text) {
    long begin = TimeUtil.getNowTime();
    try {
      Thread.sleep(MathUtil.getRdTime(1000));
    } catch (InterruptedException e) {
      log.warn("search error, e = {}", e);
    }
    log.info("news search end cost={} ms", TimeUtil.getNowTime() - begin);
    return new SearchResult("news search result!");
  }
}
