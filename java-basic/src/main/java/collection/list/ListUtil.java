package collection.list;

import java.util.Arrays;
import java.util.List;

/**
 * @author min
 */
public class ListUtil {

  /**
   * 两个集合是否有相等的元素
   *
   * @param listA
   * @param listB
   * @return
   */
  public static boolean isHasSame(List<String> listA, List<String> listB) {
    return listA.stream().anyMatch(item -> listB.stream().anyMatch(item::equals));
  }

  /**
   * A中的元素时候都在B中出现过
   *
   * @param listA
   * @param listB
   * @return
   */
  public static boolean isCom(List<String> listA, List<String> listB) {
    return listA.stream().allMatch(item -> listB.stream().anyMatch(item::equals));
  }


  public static void main(String[] args) {
    List<String> listA = Arrays.asList("a", "b", "b", "c");
    List<String> listB = Arrays.asList("a", "f", "e", "c", "d");
    System.out.println(isHasSame(listA, listB));
    System.out.println(isCom(listA, listB));
  }
}
