package chapter_8_arrayandmatrix;

public class Problem_12_LongestLessSumSubArrayLength {

  public static int maxLength(int[] arr, int k) {
    int[] h = new int[arr.length + 1];
    int sum = 0;
    h[0] = sum;
    for (int i = 0; i != arr.length; i++) {
      sum += arr[i];
      h[i + 1] = Math.max(sum, h[i]);
    }
    sum = 0;
    int res = 0;
    int pre = 0;
    int len = 0;
    for (int i = 0; i != arr.length; i++) {
      sum += arr[i];
      pre = getLessIndex(h, sum - k);
      len = pre == -1 ? 0 : i - pre + 1;
      res = Math.max(res, len);
    }
    return res;
  }

  public static int getLessIndex(int[] arr, int num) {
    int low = 0;
    int high = arr.length - 1;
    int mid = 0;
    int res = -1;
    while (low <= high) {
      mid = (low + high) / 2;
      if (arr[mid] >= num) {
        res = mid;
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    return res;
  }

  // for test
  public static int[] generateRandomArray(int len, int maxValue) {
    int[] res = new int[len];
    for (int i = 0; i != res.length; i++) {
      res[i] = (int) (Math.random() * maxValue) - (maxValue / 3);
    }
    return res;
  }

  // for test
  public static void printArray(int[] arr) {
    for (int i = 0; i != arr.length; i++) {
      System.out.print(arr[i] + " ");
    }
    System.out.println();
  }

  public static void main(String[] args) {
    int[] arr = {3, -2, -4, 0, 6};
    int k = -2;
    System.out.println(maxLength(arr, k));

  }
}
