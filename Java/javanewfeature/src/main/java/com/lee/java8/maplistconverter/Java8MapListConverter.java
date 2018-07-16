package com.lee.java8.maplistconverter;

import com.lee.java8.maplistconverter.model.Model;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author litz-a
 */
public class Java8MapListConverter {

  public static List<Model> convertMapToListWithJava8(Map<Integer, String> map) {

    List<Model> result = map.entrySet().stream().map(entry -> new Model(entry.getKey(), entry.getValue()))
        .collect(Collectors.toList());

    return result;
  }

  public static Map<Integer, String> convertListToMapWithJava8(List<Model> lstModels) {
    // Map<Long, String> map = lstModels.stream().collect(
    // Collectors.toMap(model -> model.getId(), model -> model.getData()));

    Map<Integer, String> map = lstModels.stream().collect(Collectors.toMap(Model::getId, Model::getData));

    return map;
  }

  public static void main(String[] args) {
    List<Model> modelLst = new ArrayList<>();
    modelLst.add(new Model(1, "one"));
    modelLst.add(new Model(2, "two"));
    modelLst.add(new Model(3, "three"));

    // Convert List to Map
    Map<Integer, String> map = Java8MapListConverter.convertListToMapWithJava8(modelLst);
    System.out.println("#Result convert List to Map!");
    System.out.println(map);

    // Convert Map to List
    List<Model> newModelLst = Java8MapListConverter.convertMapToListWithJava8(map);
    System.out.println("#Result convert Map to List!");
    newModelLst.forEach(System.out::println);
  }
}
