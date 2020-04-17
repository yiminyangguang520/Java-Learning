package com.my.blog.website.controller.admin;


import com.github.pagehelper.PageInfo;
import com.my.blog.website.controller.BaseController;
import com.my.blog.website.dto.LogActions;
import com.my.blog.website.dto.Types;
import com.my.blog.website.exception.TipException;
import com.my.blog.website.modal.bo.RestResponseBo;
import com.my.blog.website.modal.vo.ContentVo;
import com.my.blog.website.modal.vo.ContentVoExample;
import com.my.blog.website.modal.vo.MetaVo;
import com.my.blog.website.modal.vo.UserVo;
import com.my.blog.website.service.IContentService;
import com.my.blog.website.service.ILogService;
import com.my.blog.website.service.IMetaService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author min
 * Created by 13 on 2017/2/21.
 */
@Controller
@RequestMapping("/admin/article")
@Transactional(rollbackFor = TipException.class)
public class ArticleController extends BaseController {

  private static final Logger LOGGER = LoggerFactory.getLogger(ArticleController.class);

  @Autowired
  private IContentService contentsService;

  @Autowired
  private IMetaService metasService;

  @Autowired
  private ILogService logService;

  /**
   * 文章列表
   */
  @GetMapping(value = "")
  public String index(@RequestParam(value = "page", defaultValue = "1") int page,
      @RequestParam(value = "limit", defaultValue = "15") int limit, HttpServletRequest request) {
    ContentVoExample contentVoExample = new ContentVoExample();
    contentVoExample.setOrderByClause("created desc");
    contentVoExample.createCriteria().andTypeEqualTo(Types.ARTICLE.getType());
    PageInfo<ContentVo> contentsPaginator = contentsService.getArticlesWithpage(contentVoExample, page, limit);
    request.setAttribute("articles", contentsPaginator);
    return "admin/article_list";
  }

  /**
   * 文章发表
   */
  @GetMapping(value = "/publish")
  public String newArticle(HttpServletRequest request) {
    List<MetaVo> categories = metasService.getMetas(Types.CATEGORY.getType());
    request.setAttribute("categories", categories);
    return "admin/article_edit";
  }

  /**
   * 文章编辑
   */
  @GetMapping(value = "/{cid}")
  public String editArticle(@PathVariable String cid, HttpServletRequest request) {
    ContentVo contents = contentsService.getContents(cid);
    request.setAttribute("contents", contents);
    List<MetaVo> categories = metasService.getMetas(Types.CATEGORY.getType());
    request.setAttribute("categories", categories);
    request.setAttribute("active", "article");
    return "admin/article_edit";
  }

  /**
   * 文章发表
   */
  @PostMapping(value = "/publish")
  @ResponseBody
  @Transactional(rollbackFor = TipException.class)
  public RestResponseBo publishArticle(ContentVo contents, HttpServletRequest request) {
    UserVo users = this.user(request);
    contents.setAuthorId(users.getUid());
    contents.setType(Types.ARTICLE.getType());
    if (StringUtils.isBlank(contents.getCategories())) {
      contents.setCategories("默认分类");
    }
    try {
      contentsService.publish(contents);
    } catch (Exception e) {
      String msg = "文章发布失败";
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
   * 文章更新
   */
  @PostMapping(value = "/modify")
  @ResponseBody
  @Transactional(rollbackFor = TipException.class)
  public RestResponseBo modifyArticle(ContentVo contents, HttpServletRequest request) {
    UserVo users = this.user(request);
    contents.setAuthorId(users.getUid());
    contents.setType(Types.ARTICLE.getType());
    try {
      contentsService.updateArticle(contents);
    } catch (Exception e) {
      String msg = "文章编辑失败";
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
   * 删除文章
   */
  @RequestMapping(value = "/delete")
  @ResponseBody
  @Transactional(rollbackFor = TipException.class)
  public RestResponseBo delete(@RequestParam int cid, HttpServletRequest request) {
    try {
      contentsService.deleteByCid(cid);
      logService.insertLog(LogActions.DEL_ARTICLE.getAction(), cid + "", request.getRemoteAddr(), this.getUid(request));
    } catch (Exception e) {
      String msg = "文章删除失败";
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
