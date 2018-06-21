package org.shiro.security.modules.sys.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.io.File;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.shiro.common.utils.Result;
import org.shiro.security.common.annotation.SysLog;
import org.shiro.security.common.utils.Constant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * @author yuanpb
 * @version 创建时间：2018年4月26日 下午1:28:38 类说明 :文件上传
 */
@RestController
@RequestMapping("/sys/file")
@Api(value = "文件上传")
public class FileOperate {

  @Value("${file.path}")
  private String path;

  /**
   * 实现文件上传
   */
  @PostMapping("fileUpload")
  @ResponseBody
  @SysLog("文件上传")
  @RequiresPermissions("sys:file:fileUpload")
  @ApiOperation(value = "文件上传", httpMethod = "POST", produces = "application/json", response = Result.class)
  public Result fileUpload(@RequestParam("fileName") MultipartFile file) {
    if (file.isEmpty()) {
      return Result.error(Constant.FILEEMP);
    }
    String fileName = file.getOriginalFilename();
    int size = (int) file.getSize();
    System.out.println(fileName + "-->" + size);
    File dest = new File(path + "/" + fileName);
    if (!dest.getParentFile().exists()) { // 判断文件父目录是否存在
      dest.getParentFile().mkdir();
    }
    try {
      file.transferTo(dest); // 保存文件
      return Result.ok(Constant.FILESUCESS);
    } catch (Exception e) {
      return Result.error(Constant.FILEFAILE);
    }
  }

  /**
   * 实现多文件上传
   */
  @PostMapping(value = "multifileUpload")
  @RequiresPermissions("sys:file:multifileUpload")
  @ResponseBody
  @SysLog("多文件上传")
  @ApiOperation(value = "多文件上传", httpMethod = "POST", produces = "application/json", response = Result.class)
  public Result multifileUpload(HttpServletRequest request) {
    List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("fileName");
    if (files.isEmpty()) {
      return Result.error(Constant.FILEEMP);
    }
    for (MultipartFile file : files) {
      String fileName = file.getOriginalFilename();
      int size = (int) file.getSize();
      System.out.println(fileName + "-->" + size);
      if (file.isEmpty()) {
        return Result.error(Constant.FILEEMP);
      } else {
        File dest = new File(path + "/" + fileName);
        if (!dest.getParentFile().exists()) { // 判断文件父目录是否存在
          dest.getParentFile().mkdir();
        }
        try {
          file.transferTo(dest);
        } catch (Exception e) {
          return Result.error(Constant.FILEFAILE);
        }
      }
    }
    return Result.ok(Constant.FILESUCESS);
  }
}
