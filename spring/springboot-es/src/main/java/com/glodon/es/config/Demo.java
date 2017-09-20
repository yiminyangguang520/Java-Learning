package com.glodon.es.config;

import com.alibaba.fastjson.JSON;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author sunyd 【sunyd@glodon.com】
 * @Date 2017/9/12 15:30
 */
public class Demo {

  public static void main(String[] args) throws Exception{
    List<Entity> list = new ArrayList<>();
    Entity entity1 = new Entity("c:\\temp\\a.txt","a.txt");
    Entity entity2 = new Entity("c:\\temp\\b.txt","b.txt");
    Entity entity3 = new Entity("c:\\work\\a.txt","a.txt");
    Entity entity4 = new Entity("c:\\dd\\a.txt","a.txt");
    Entity entity5 = new Entity("c:\\dd\\b.txt","b.txt");
    list.add(entity1);
    list.add(entity2);
    list.add(entity3);
    list.add(entity4);
    list.add(entity5);

    Set<String> set = new HashSet<>();//a.html,b.html

    int k = 0;
    for(int i=0;i<list.size();i++){
      Entity entity = list.get(i);

      String basePath = "c:\\GEH\\";
      String newStr =  basePath+"uuid_"+entity.getValue();
      if(!set.contains(newStr)){
        String destPath = basePath+"uuid_"+entity.getValue();
        set.add(destPath);
        copyFile(new File(entity.getKey()),new File(destPath));
      }else{
        k = k+ 1;
        String fileName = entity.getValue().split("\\.")[0];
        String fileSuffix = entity.getValue().split("\\.")[1];
        String destPath = basePath+"uuid_"+fileName+k+"."+fileSuffix;
        set.add(destPath);
        copyFile(new File(entity.getKey()),new File(destPath));
      }
    }

    System.out.println(JSON.toJSONString(set));
    /*for(int j=0;j<list.size();j++){
      Entity entity = list.get(j);
      String val = entity.getValue();
    }

    System.out.println(JSON.toJSONString(list));*/

  }

  public static void copyFile(File sourceFile,File destFile) throws Exception{
    FileInputStream fis = new FileInputStream(sourceFile);
    FileOutputStream fos = new FileOutputStream(destFile);
    byte [] buffer = new byte[1024];
    int len = 0;
    while((len = fis.read(buffer)) != -1){
      fos.write(buffer,0,len);
    }
  }
}
