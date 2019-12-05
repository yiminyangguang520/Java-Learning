package collection.map;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * @author litz-a
 */
public class SortByKey {

  public static void main(String... args) {
    sortMapByKey();
    sortMapByKeyReverse();
    sortByValueLambda();
  }

  private static void sortMapByKey() {
    Map<Integer, String> random = new HashMap<>(10);
    for (int i = 0; i < 10; i++) {
      random.put((int) (Math.random() * 100), String.valueOf((int) (Math.random() * 100)));
    }

    System.out.println("Initial Map: " + Arrays.toString(random.entrySet().toArray()));

    TreeMap<Integer, String> sorted = new TreeMap<>(random);
    System.out.println("Sorted Map: " + Arrays.toString(sorted.entrySet().toArray()));
  }

  private static void sortMapByKeyReverse() {
    Map<Integer, String> random = new HashMap<>(10);
    for (int i = 0; i < 10; i++) {
      random.put((int) (Math.random() * 100), String.valueOf((int) (Math.random() * 100)));
    }

    System.out.println("Initial Map: " + Arrays.toString(random.entrySet().toArray()));

    TreeMap<Integer, String> sorted = new TreeMap<>(Comparator.reverseOrder());
    sorted.putAll(random);
    System.out.println("Sorted Map: " + Arrays.toString(sorted.entrySet().toArray()));
  }

  private void sortMapByValue() {
    Map<Integer, String> random = new HashMap<>(10);
    for (int i = 0; i < 10; i++) {
      random.put((int) (Math.random() * 100), String.valueOf((int) (Math.random() * 100)));
    }

    System.out.println("Initial Map: " + Arrays.toString(random.entrySet().toArray()));

    TreeMap<Integer, String> sorted = new TreeMap<>(new ValueComparator(random));
    sorted.putAll(random);
    System.out.println("Sorted Map: " + Arrays.toString(sorted.entrySet().toArray()));
  }

  private static void sortByValueLambda() {
    Map<Integer, String> random = new HashMap<>(10);
    for (int i = 0; i < 10; i++) {
      random.put((int) (Math.random() * 100), String.valueOf((int) (Math.random() * 100)));
    }

    System.out.println("Initial Map: " + Arrays.toString(random.entrySet().toArray()));

    Map<Integer, String> sortedMap = random.entrySet().stream()
      .sorted(Map.Entry.comparingByValue())
      .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    System.out.println("Sorted Map: " + Arrays.toString(sortedMap.entrySet().toArray()));
  }

  public class ValueComparator implements Comparator<Integer> {

    private Map<Integer, String> map;

    public ValueComparator(Map<Integer, String> map) {
      this.map = map;
    }

    @Override
    public int compare(Integer a, Integer b) {
      return map.get(a).compareTo(map.get(b));
    }
  }
}
