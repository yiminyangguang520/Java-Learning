package org.newtutorials.elasticsearch.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

/**
 *
 * @author dani
 * @date 5/7/2017
 */
public class FileUtil {
  //        You can combine NIO 2 and the Stream API.

  public static void deleteFolder(String tempFolder) {
    Path rootPath = Paths.get(tempFolder);
    try {
      Files.walk(rootPath)
          .sorted(Comparator.reverseOrder())
          .peek(System.out::println)
          .map(Path::toFile)
          .forEach(File::delete);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
