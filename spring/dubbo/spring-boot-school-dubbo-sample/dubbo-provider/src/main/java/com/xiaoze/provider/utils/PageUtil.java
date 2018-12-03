package com.xiaoze.provider.utils;

import com.github.pagehelper.PageInfo;
import com.xiaoze.api.entity.CourseType;
import com.xiaoze.api.entity.Page;

/**
 * PageUtil
 *
 * @author xiaoze
 * @date 2018/6/11
 */
public class PageUtil {

  public Page<CourseType> getPage(PageInfo<CourseType> pageInfo) {
    Page<CourseType> page = new Page<>();

    page.setPageNum(pageInfo.getPageNum());

    page.setPageSize(pageInfo.getPageSize());

    page.setSize(pageInfo.getSize());

    page.setTotal(pageInfo.getTotal());

    page.setList(pageInfo.getList());

    page.setStartRow(pageInfo.getStartRow());

    page.setEndRow(pageInfo.getEndRow());

    page.setPages(pageInfo.getPages());

    page.setPrePage(pageInfo.getPrePage());

    page.setNextPage(pageInfo.getNextPage());

    page.setFirstPage(pageInfo.isIsFirstPage());

    page.setLastPage(pageInfo.isIsLastPage());

    page.setHasPreviousPage(pageInfo.isHasPreviousPage());

    page.setHasNextPage(pageInfo.isHasNextPage());

    page.setNavigatepageNums(pageInfo.getNavigatepageNums());

    page.setNavigateFirstPage(pageInfo.getNavigateFirstPage());

    page.setNavigateLastPage(pageInfo.getNavigateLastPage());

    return page;

  }

}
