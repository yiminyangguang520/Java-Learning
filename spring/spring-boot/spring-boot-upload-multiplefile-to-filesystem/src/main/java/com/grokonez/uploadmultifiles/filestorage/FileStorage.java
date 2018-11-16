package com.grokonez.uploadmultifiles.filestorage;

import java.nio.file.Path;
import java.util.stream.Stream;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author litz-a
 */
public interface FileStorage {

  void store(MultipartFile file);

  Resource loadFile(String filename);

  void deleteAll();

  void init();

  Stream<Path> loadFiles();
}
