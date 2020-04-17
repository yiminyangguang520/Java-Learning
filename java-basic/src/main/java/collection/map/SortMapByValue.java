package collection.map;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author min
 */
public class SortMapByValue {

  public static void main(String[] args) {

    Map<String, Integer> map = new HashMap<>(16);
    map.put("Niraj", 6);
    map.put("Rahul", 43);
    map.put("Ram", 44);
    map.put("Sham", 33);
    map.put("Pratik", 5);

    System.out.println("Input Map " + map);

    //Sort map by descending order
    Map<String, Integer> sortedMapByValueDesc = map.entrySet().stream()
      .sorted(Map.Entry.comparingByValue())
      .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

    System.out.println("Sorted Map " + sortedMapByValueDesc);

    //Sort map by ascending order
    Map<String, Integer> sortedMapByValueasce = map.entrySet().stream()
      .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
      .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    System.out.println("Sorted Map " + sortedMapByValueasce);
  }

}
