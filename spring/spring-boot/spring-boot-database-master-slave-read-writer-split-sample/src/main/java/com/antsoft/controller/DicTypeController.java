package com.antsoft.controller;

import com.antsoft.model.DicType;
import com.antsoft.service.DicTypeService;
import com.github.pagehelper.PageInfo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Jason
 * @date 2017/3/2
 */
@RestController
@RequestMapping("/dictype")
public class DicTypeController {

  @Autowired
  private DicTypeService dicTypeService;

  @RequestMapping(value = "/all")
  public PageInfo<DicType> getALL(DicType dicType) {
    List<DicType> dicTypeList = dicTypeService.getAll(dicType);
    return new PageInfo<>(dicTypeList);
  }
}
