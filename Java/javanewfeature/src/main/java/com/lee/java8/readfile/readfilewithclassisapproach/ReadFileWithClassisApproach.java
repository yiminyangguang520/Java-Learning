package com.lee.java8.readfile.readfilewithclassisapproach;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author min
 */
public class ReadFileWithClassisApproach {

  public static void main(String[] args) {
    BufferedReader br = null;
    try {
      br = new BufferedReader(new FileReader("C://readfile/input.txt"));
      String line;
      while (null != (line = br.readLine())) {
        // process each line of File
        System.out.println(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (null != br) {
          br.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
