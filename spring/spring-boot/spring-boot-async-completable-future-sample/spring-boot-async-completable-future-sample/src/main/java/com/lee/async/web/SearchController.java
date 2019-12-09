package com.lee.async.web;

import com.lee.async.facade.SearchFacade;
import com.lee.async.model.SearchResult;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bruce
 * @date 2019/10/22
 * @description
 */
@RestController
@RequestMapping("/search")
public class SearchController {

  @Resource
  private SearchFacade searchFacade;

  @GetMapping("/all")
  public SearchResult searchAll(String text) {
    return searchFacade.searchAll(text);
  }
}
