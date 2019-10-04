package org.lee.mybatis.controller;

import java.util.List;
import org.lee.mybatis.model.Institute;
import org.lee.mybatis.service.InstituteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author bruce
 */
@RestController
public class InstituteController implements BaseController<Institute, Integer> {

  @Autowired
  private InstituteService instituteService;

  @Override
  @RequestMapping(value = "/api/institute/{id}", method = RequestMethod.DELETE)
  public int deleteById(@PathVariable("id") Integer id) {
    return instituteService.deleteById(id);
  }

  @Override
  @RequestMapping(value = "/api/institute", method = RequestMethod.POST)
  public int save(@RequestBody Institute institute) {
    return instituteService.save(institute);
  }

  @Override
  @RequestMapping(value = "/api/institutes", method = RequestMethod.GET)
  public List<Institute> findAll() {
    return instituteService.findAll();
  }

  @Override
  @RequestMapping(value = "/api/institute/{id}", method = RequestMethod.GET)
  public Institute findById(@PathVariable("id") Integer id) {
    return instituteService.findById(id);
  }

  @RequestMapping(value = "/api/institute", method = RequestMethod.PUT)
  public int update(@RequestBody Institute institute) {
    return instituteService.update(institute);
  }
}
