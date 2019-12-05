package collection.map;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author litz-a
 */
public class SortMapByKeyWithCustomComparator {

  public static void main(String[] args) {

    Map<Name, Integer> map = new HashMap<>(16);
    Name name0 = Name.builder().firstName("Zendor").lastName("Sonawane").build();
    Name name1 = Name.builder().firstName("Niraj").lastName("Sonawane").build();
    Name name2 = Name.builder().firstName("Pratik").lastName("Sonawane").build();
    Name name3 = Name.builder().firstName("Jeetesh").lastName("Sonawane").build();
    Name name4 = Name.builder().firstName("Rahul").lastName("Sonawane").build();
    Name name5 = Name.builder().firstName("Amit").lastName("Sonawane").build();

    map.put(name0, 55);
    map.put(name1, 1);
    map.put(name2, 2);
    map.put(name3, 3);
    map.put(name4, 4);
    map.put(name5, 5);

    System.out.println("Input Map " + map);

    //Sort map by descending order
    Comparator<Name> byName = (Name o1, Name o2) -> o1.getFirstName().compareTo(o2.getFirstName());

    LinkedHashMap<Name, Integer> sortedMapByValueDesc = map.entrySet().stream()
        .sorted(Map.Entry.<Name, Integer>comparingByKey(byName))
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

    System.out.println("Sorted Map " + sortedMapByValueDesc);

    //Sort map by ascending order
    LinkedHashMap<Name, Integer> sortedMapByValueAsce = map.entrySet().stream()
        .sorted(Map.Entry.<Name, Integer>comparingByKey(byName).reversed())
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

    System.out.println("Sorted Map " + sortedMapByValueAsce);
  }

}