package com.grokonez.uploadmultifiles;

import com.grokonez.uploadmultifiles.filestorage.FileStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author litz-a
 */
@SpringBootApplication
public class SpringBootUploadMultiFileToFileSystemApplication implements CommandLineRunner {

  @Autowired
  private FileStorage fileStorage;

  public static void main(String[] args) {
    SpringApplication.run(SpringBootUploadMultiFileToFileSystemApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    fileStorage.deleteAll();
    fileStorage.init();
  }
}
