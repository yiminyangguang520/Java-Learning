package collection.map;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author min
 */
public class SortMapByKey {

  public static void main(String[] args) {

    Map<String, Integer> map = new HashMap<>(6);
    map.put("Niraj", 6);
    map.put("Rahul", 43);
    map.put("Ram", 44);
    map.put("Sham", 33);
    map.put("Pratik", 5);
    map.put("Ashok", 5);

    System.out.println("Input Map " + map);

    //Sorted map by descending order
    Map<String, Integer> sortedMapByValueDesc = map.entrySet().stream()
      .sorted(Map.Entry.comparingByKey())
      .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

    System.out.println("Sorted Map " + sortedMapByValueDesc);

    //Sorted map by ascending order

    Map<String, Integer> sortedMapByValueasce = map.entrySet().stream()
      .sorted(Map.Entry.<String, Integer>comparingByKey().reversed())
      .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    System.out.println("Sorted Map " + sortedMapByValueasce);
  }

}
