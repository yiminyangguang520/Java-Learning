package com.my.blog.website.controller.admin;

import com.my.blog.website.constant.WebConst;
import com.my.blog.website.controller.BaseController;
import com.my.blog.website.dto.LogActions;
import com.my.blog.website.exception.TipException;
import com.my.blog.website.modal.bo.BackResponseBo;
import com.my.blog.website.modal.bo.RestResponseBo;
import com.my.blog.website.modal.vo.OptionVo;
import com.my.blog.website.service.ILogService;
import com.my.blog.website.service.IOptionService;
import com.my.blog.website.service.ISiteService;
import com.my.blog.website.utils.GsonUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
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
 * Created by wangq on 2017/3/20.
 */
@Controller
@RequestMapping("/admin/setting")
public class SettingController extends BaseController {

  private static final Logger LOGGER = LoggerFactory.getLogger(SettingController.class);

  @Autowired
  private IOptionService optionService;

  @Autowired
  private ILogService logService;

  @Autowired
  private ISiteService siteService;

  /**
   * 系统设置
   */
  @GetMapping(value = "")
  public String setting(HttpServletRequest request) {
    List<OptionVo> voList = optionService.getOptions();
    Map<String, String> options = new HashMap<>();
    voList.forEach((option) -> {
      options.put(option.getName(), option.getValue());
    });
    request.setAttribute("options", options);
    return "admin/setting";
  }

  /**
   * 保存系统设置
   */
  @PostMapping(value = "")
  @ResponseBody
  @Transactional(rollbackFor = TipException.class)
  public RestResponseBo saveSetting(@RequestParam(required = false) String site_theme, HttpServletRequest request) {
    try {
      Map<String, String[]> parameterMap = request.getParameterMap();
      Map<String, String> querys = new HashMap<>();
      parameterMap.forEach((key, value) -> {
        querys.put(key, join(value));
      });

      optionService.saveOptions(querys);

      WebConst.initConfig = querys;

      if (StringUtils.isNotBlank(site_theme)) {
        BaseController.THEME = "themes/" + site_theme;
      }
      logService.insertLog(LogActions.SYS_SETTING.getAction(), GsonUtils.toJsonString(querys), request.getRemoteAddr(), this.getUid(request));
      return RestResponseBo.ok();
    } catch (Exception e) {
      String msg = "保存设置失败";
      if (e instanceof TipException) {
        msg = e.getMessage();
      } else {
        LOGGER.error(msg, e);
      }
      return RestResponseBo.fail(msg);
    }
  }


  /**
   * 系统备份
   */
  @PostMapping(value = "backup")
  @ResponseBody
  @Transactional(rollbackFor = TipException.class)
  public RestResponseBo backup(@RequestParam String bk_type, @RequestParam String bk_path,
      HttpServletRequest request) {
    if (StringUtils.isBlank(bk_type)) {
      return RestResponseBo.fail("请确认信息输入完整");
    }
    try {
      BackResponseBo backResponse = siteService.backup(bk_type, bk_path, "yyyyMMddHHmm");
      logService.insertLog(LogActions.SYS_BACKUP.getAction(), null, request.getRemoteAddr(), this.getUid(request));
      return RestResponseBo.ok(backResponse);
    } catch (Exception e) {
      String msg = "备份失败";
      if (e instanceof TipException) {
        msg = e.getMessage();
      } else {
        LOGGER.error(msg, e);
      }
      return RestResponseBo.fail(msg);
    }
  }


  /**
   * 数组转字符串
   */
  private String join(String[] arr) {
    StringBuilder ret = new StringBuilder();
    String[] var3 = arr;
    int var4 = arr.length;

    for (int var5 = 0; var5 < var4; ++var5) {
      String item = var3[var5];
      ret.append(',').append(item);
    }
    return ret.length() > 0 ? ret.substring(1) : ret.toString();
  }

}