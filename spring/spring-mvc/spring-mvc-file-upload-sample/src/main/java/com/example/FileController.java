package com.example;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.Callable;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

/**
 *
 * @author pkpk1234
 * @date 2017/5/5
 */
@RestController
@RequestMapping(path = "/files")
public class FileController {

  private static Logger logger = LoggerFactory.getLogger(FileController.class);

  private Path upload_files = Paths.get("upload_files");

  @GetMapping(path = "/read/{filename:.+}", name = "read")
  public ResponseEntity<?> getFile(@PathVariable("filename") String filename) {
    Path filePath = upload_files.resolve(filename);
    File file = filePath.toFile();
    logger.info("file >>>>>> " + file.getAbsolutePath());
    if (file.exists()) {
      Resource resource = new FileSystemResource(file);
      return ResponseEntity.ok().contentType(MediaType.TEXT_PLAIN).body(resource);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  /**
   * async
   * @param file
   * @return
   * @throws Exception
   */
  @PostMapping("/upload")
  public Callable<ResponseEntity<?>> uploadFile(@RequestParam MultipartFile file) throws Exception {
    // run in web container thread pool
    logger.info("web container thread >>>> " + Thread.currentThread().toString());

    //run in async thread pool
    return () -> {
      logger.info("upload thread >>>>>> " + Thread.currentThread().toString());
      String fileName = file.getOriginalFilename();
      try(InputStream inputStream = file.getInputStream()) {
        if (!upload_files.toFile().exists()) {
          upload_files.toFile().mkdirs();
        }
        Path target = upload_files.resolve(fileName);
        logger.info("target >>>>>> " + target.toAbsolutePath());
        Files.copy(inputStream, target, StandardCopyOption.REPLACE_EXISTING);
      }

      URI fileUri = URI.create(MvcUriComponentsBuilder.fromMappingName("read").build() + fileName);
      logger.info("fileUri >>>>" + fileUri);
      return ResponseEntity.created(fileUri).contentType(MediaType.TEXT_PLAIN).body("Create Successful");
    };
  }
}
