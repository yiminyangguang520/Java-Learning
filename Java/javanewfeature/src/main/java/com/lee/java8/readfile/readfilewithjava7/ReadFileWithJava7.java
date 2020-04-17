package com.lee.java8.readfile.readfilewithjava7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author min
 */
public class ReadFileWithJava7 {

  public static void main(String[] args) {
    try (BufferedReader br = new BufferedReader(new FileReader("C://readfile/input.txt"))) {
      String line;
      while (null != (line = br.readLine())) {
        // processing each line of file
        System.out.println(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
