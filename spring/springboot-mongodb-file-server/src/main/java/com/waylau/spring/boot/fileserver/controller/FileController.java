package com.waylau.spring.boot.fileserver.controller;

import com.waylau.spring.boot.fileserver.domain.File;
import com.waylau.spring.boot.fileserver.service.FileService;
import com.waylau.spring.boot.fileserver.util.MD5Util;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author litz-a
 * 允许所有域名访问
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
public class FileController {

  @Autowired
  private FileService fileService;

  @Value("${server.address}")
  private String serverAddress;

  @Value("${server.port}")
  private String serverPort;

  @RequestMapping(value = "/")
  public String index(Model model) {
    // 展示最新二十条数据
    model.addAttribute("files", fileService.listFilesByPage(0, 20));
    return "index";
  }

  /**
   * 分页查询文件
   */
  @GetMapping("files/{pageIndex}/{pageSize}")
  @ResponseBody
  public List<File> listFilesByPage(@PathVariable int pageIndex, @PathVariable int pageSize) {
    return fileService.listFilesByPage(pageIndex, pageSize);
  }

  /**
   * 获取文件片信息
   */
  @GetMapping("files/{id}")
  @ResponseBody
  public ResponseEntity<Object> serveFile(@PathVariable String id) throws UnsupportedEncodingException {

    File file = fileService.getFileById(id);

    if (file != null) {
      return ResponseEntity.ok()
          .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=" + new String(file.getName().getBytes("utf-8"), "ISO-8859-1"))
          .header(HttpHeaders.CONTENT_TYPE, "application/octet-stream")
          .header(HttpHeaders.CONTENT_LENGTH, file.getSize() + "").header("Connection", "close")
          .body(file.getContent().getData());
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File was not fount");
    }

  }

  /**
   * 在线显示文件
   */
  @GetMapping("/view/{id}")
  @ResponseBody
  public ResponseEntity<Object> serveFileOnline(@PathVariable String id) {
    File file = fileService.getFileById(id);
    if (file != null) {
      return ResponseEntity.ok()
          .header(HttpHeaders.CONTENT_DISPOSITION, "fileName=\"" + file.getName() + "\"")
          .header(HttpHeaders.CONTENT_TYPE, file.getContentType())
          .header(HttpHeaders.CONTENT_LENGTH, file.getSize() + "").header("Connection", "close")
          .body(file.getContent().getData());
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File was not fount");
    }
  }

  /**
   * 上传
   */
  @PostMapping("/")
  public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
    try {
      File f = new File(file.getOriginalFilename(), file.getContentType(), file.getSize(),
          new Binary(file.getBytes()));
      f.setMd5(MD5Util.getMD5(file.getInputStream()));
      fileService.saveFile(f);
    } catch (IOException | NoSuchAlgorithmException ex) {
      ex.printStackTrace();
      redirectAttributes.addFlashAttribute("message", "Your " + file.getOriginalFilename() + " is wrong!");
      return "redirect:/";
    }

    redirectAttributes.addFlashAttribute("message",
        "You successfully uploaded " + file.getOriginalFilename() + "!");

    return "redirect:/";
  }

  /**
   * 上传接口
   */
  @PostMapping("/upload")
  @ResponseBody
  public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
    File returnFile = null;
    try {
      File f = new File(file.getOriginalFilename(), file.getContentType(), file.getSize(),
          new Binary(file.getBytes()));
      f.setMd5(MD5Util.getMD5(file.getInputStream()));
      returnFile = fileService.saveFile(f);
      String path = "//" + serverAddress + ":" + serverPort + "/view/" + returnFile.getId();
      return ResponseEntity.status(HttpStatus.OK).body(path);

    } catch (IOException | NoSuchAlgorithmException ex) {
      ex.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

  }

  /**
   * 删除文件
   */
  @DeleteMapping("/{id}")
  @ResponseBody
  public ResponseEntity<String> deleteFile(@PathVariable String id) {
    try {
      fileService.removeFile(id);
      return ResponseEntity.status(HttpStatus.OK).body("DELETE Success!");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
  }
}
