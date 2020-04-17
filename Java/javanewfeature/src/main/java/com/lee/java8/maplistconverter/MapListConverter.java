package com.lee.java8.maplistconverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author min
 */
public class MapListConverter {

  private static List<Model> convertMapToListWithJava8(Map<Integer, String> map) {

    return map.entrySet().stream()
        .map(entry -> new Model(entry.getKey(), entry.getValue()))
        .collect(Collectors.toList());
  }

  private static Map<Integer, String> convertListToMapWithJava8(List<Model> lstModels) {
    return lstModels.stream().collect(Collectors.toMap(Model::getId, Model::getData));
  }

  public static void main(String[] args) {
    List<Model> modelLst = new ArrayList<>();
    modelLst.add(new Model(1, "one"));
    modelLst.add(new Model(2, "two"));
    modelLst.add(new Model(3, "three"));

    // Convert List to Map
    Map<Integer, String> map = MapListConverter.convertListToMapWithJava8(modelLst);
    System.out.println("#Result convert List to Map!");
    System.out.println(map);

    // Convert Map to List
    List<Model> newModelLst = MapListConverter.convertMapToListWithJava8(map);
    System.out.println("#Result convert Map to List!");
    newModelLst.forEach(System.out::println);
  }
}
