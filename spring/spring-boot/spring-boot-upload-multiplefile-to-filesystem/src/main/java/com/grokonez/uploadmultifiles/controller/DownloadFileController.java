package com.grokonez.uploadmultifiles.controller;

import com.grokonez.uploadmultifiles.filestorage.FileStorage;
import com.grokonez.uploadmultifiles.model.FileInfo;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

/**
 * @author litz-a
 */
@Controller
public class DownloadFileController {

  @Autowired
  private FileStorage fileStorage;

  /**
   * Retrieve Files' Information
   * @param model
   * @return
   */
  @GetMapping("/files")
  public String getListFiles(Model model) {
    List<FileInfo> fileInfos = fileStorage.loadFiles().map(
        path -> {
          String filename = path.getFileName().toString();
          String url = MvcUriComponentsBuilder.fromMethodName(DownloadFileController.class,
              "downloadFile", path.getFileName().toString()).build().toString();
          return new FileInfo(filename, url);
        }
    )
        .collect(Collectors.toList());

    model.addAttribute("files", fileInfos);
    return "listfiles";
  }

  /**
   * Download Files
   */
  @GetMapping("/files/{filename}")
  public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {
    Resource file = fileStorage.loadFile(filename);
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
        .body(file);
  }
}