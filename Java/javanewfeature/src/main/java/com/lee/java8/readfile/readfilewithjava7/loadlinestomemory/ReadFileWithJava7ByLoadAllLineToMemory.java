package com.lee.java8.readfile.readfilewithjava7.loadlinestomemory;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author min
 */
public class ReadFileWithJava7ByLoadAllLineToMemory {

  public static void main(String[] args) {
    try {
      List<String> lines = Files.readAllLines(Paths.get("C://readfile/input.txt"));
      for (String line : lines) {
        System.out.println(line);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
