package collection.map;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author min
 */
public class SortMapByValueWithCustomComparator {

  public static void main(String[] args) {

    Map<Name, Age> map = new HashMap<>(16);
    Name name0 = Name.builder().firstName("Zendor").lastName("Sonawane").build();
    Name name1 = Name.builder().firstName("Niraj").lastName("Sonawane").build();
    Name name2 = Name.builder().firstName("Pratik").lastName("Sonawane").build();
    Name name3 = Name.builder().firstName("Jeetesh").lastName("Sonawane").build();
    Name name4 = Name.builder().firstName("Rahul").lastName("Sonawane").build();
    Name name5 = Name.builder().firstName("Amit").lastName("Sonawane").build();

    Age age0 = Age.builder().year(30).month(5).build();
    Age age1 = Age.builder().year(66).month(3).build();
    Age age2 = Age.builder().year(17).month(6).build();
    Age age3 = Age.builder().year(3).month(5).build();
    Age age4 = Age.builder().year(50).month(5).build();
    Age age5 = Age.builder().year(80).month(12).build();

    map.put(name0, age0);
    map.put(name1, age1);
    map.put(name2, age2);
    map.put(name3, age3);
    map.put(name4, age4);
    map.put(name5, age5);

    System.out.println("Input Map " + map);

    //Sort map by descending order

    Comparator<Age> byAge = (Age obj1, Age obj2) -> obj1.getYear().compareTo(obj2.getYear());

    LinkedHashMap<Name, Age> sortedMapByValueDesc = map.entrySet().stream()
      .sorted(Map.Entry.<Name, Age>comparingByValue(byAge))
      .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

    System.out.println("Sorted Map " + sortedMapByValueDesc);

    //Sort map by ascending order
    LinkedHashMap<Name, Age> sortedMapByValueAsce = map.entrySet().stream()
      .sorted(Map.Entry.<Name, Age>comparingByValue(byAge).reversed())
      .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

    System.out.println("Sorted Map " + sortedMapByValueAsce);


  }

}
