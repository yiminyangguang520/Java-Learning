package com.lee.dp.adapter.example4;

import java.io.*;
import java.util.*;
import lombok.Cleanup;

/**
 * 实现对日志文件的操作
 * @author bruce
 */
public class LogFileOperate implements LogFileOperateApi {

  /**
   * 日志文件的路径和文件名称，默认是当前classpath下的AdapterLog.log
   */
  private String logFilePathName = "AdapterLog.log";

  /**
   * 构造方法，传入文件的路径和名称
   *
   * @param logFilePathName 文件的路径和名称
   */
  public LogFileOperate(String logFilePathName) {
    //先判断是否传入了文件的路径和名称，如果是，
    //就重新设置操作的日志文件的路径和名称
    if (logFilePathName != null && logFilePathName.trim().length() > 0) {
      this.logFilePathName = logFilePathName;
    }
  }

  @Override
  public List<LogModel> readLogFile() {
    List<LogModel> list = new ArrayList<>(10);
    try {
      File f = new File(logFilePathName);
      if (f.exists()) {
        @Cleanup ObjectInputStream oin = new ObjectInputStream(
          new BufferedInputStream(new FileInputStream(f))
        );
        list = (List<LogModel>) oin.readObject();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return list;
  }

  @Override
  public void writeLogFile(List<LogModel> list) {
    File f = new File(logFilePathName);
    try {
      @Cleanup ObjectOutputStream oout = new ObjectOutputStream(
        new BufferedOutputStream(new FileOutputStream(f))
      );
      oout.writeObject(list);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
