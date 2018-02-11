package com.my.blog.website.controller.admin;

import com.my.blog.website.constant.WebConst;
import com.my.blog.website.controller.BaseController;
import com.my.blog.website.dto.MetaDto;
import com.my.blog.website.dto.Types;
import com.my.blog.website.exception.TipException;
import com.my.blog.website.modal.bo.RestResponseBo;
import com.my.blog.website.service.IMetaService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author litz-a
 * Created by 13 on 2017/2/21.
 */
@Controller
@RequestMapping("admin/category")
public class CategoryController extends BaseController {

  private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

  @Autowired
  private IMetaService metasService;

  /**
   * 分类页
   */
  @GetMapping(value = "")
  public String index(HttpServletRequest request) {
    List<MetaDto> categories = metasService.getMetaList(Types.CATEGORY.getType(), null, WebConst.MAX_POSTS);
    List<MetaDto> tags = metasService.getMetaList(Types.TAG.getType(), null, WebConst.MAX_POSTS);
    request.setAttribute("categories", categories);
    request.setAttribute("tags", tags);
    return "admin/category";
  }

  @PostMapping(value = "save")
  @ResponseBody
  @Transactional(rollbackFor = TipException.class)
  public RestResponseBo saveCategory(@RequestParam String cname, @RequestParam Integer mid) {
    try {
      metasService.saveMeta(Types.CATEGORY.getType(), cname, mid);
    } catch (Exception e) {
      String msg = "分类保存失败";
      if (e instanceof TipException) {
        msg = e.getMessage();
      } else {
        LOGGER.error(msg, e);
      }
      return RestResponseBo.fail(msg);
    }
    return RestResponseBo.ok();
  }

  /**
   * 删除分类
   */
  @RequestMapping(value = "delete")
  @ResponseBody
  @Transactional(rollbackFor = TipException.class)
  public RestResponseBo delete(@RequestParam int mid) {
    try {
      metasService.delete(mid);
    } catch (Exception e) {
      String msg = "删除失败";
      if (e instanceof TipException) {
        msg = e.getMessage();
      } else {
        LOGGER.error(msg, e);
      }
      return RestResponseBo.fail(msg);
    }
    return RestResponseBo.ok();
  }

}
