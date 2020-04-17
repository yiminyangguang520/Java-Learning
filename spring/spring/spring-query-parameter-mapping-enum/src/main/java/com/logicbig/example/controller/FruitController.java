package com.logicbig.example.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author min
 */
@RestController
public class FruitController {

  @RequestMapping("/fruit")
  public String handleRequest(@RequestParam("name") Fruit fruit) {
    return "response for " + fruit;
  }

  @RequestMapping("/fruits")
  public String handleRequest2(@RequestParam("name") Fruit[] fruits) {
    return "response for " + Arrays.toString(fruits);
  }

  @RequestMapping("/fruitsCount")
  public String handleRequest3(@RequestParam MultiValueMap<String, Integer> queryMap) {
    String response = "";
    for (Map.Entry<String, List<Integer>> entry : queryMap.entrySet()) {
      Fruit f;
      try {
        f = Fruit.valueOf(entry.getKey());
      } catch (IllegalArgumentException e) {
        return "Not a valid fruit: " + entry.getKey();
      }
      List<Integer> value = entry.getValue();
      if (value.size() > 0) {
        response += f + "=" + value.get(0) + "<br/>";
      }
    }
    return response;
  }

  public enum Fruit {
    Apple,
    Banana,
    Orange
  }
}