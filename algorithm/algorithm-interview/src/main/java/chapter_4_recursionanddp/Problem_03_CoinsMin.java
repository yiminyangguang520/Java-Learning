package chapter_4_recursionanddp;

public class Problem_03_CoinsMin {

  public static int minCoins1(int[] arr, int aim) {
    if (arr == null || arr.length == 0 || aim < 0) {
      return -1;
    }
    int n = arr.length;
    int max = Integer.MAX_VALUE;
    int[][] dp = new int[n][aim + 1];
    for (int j = 1; j <= aim; j++) {
      dp[0][j] = max;
      if (j - arr[0] >= 0 && dp[0][j - arr[0]] != max) {
        dp[0][j] = dp[0][j - arr[0]] + 1;
      }
    }
    int left = 0;
    for (int i = 1; i < n; i++) {
      for (int j = 1; j <= aim; j++) {
        left = max;
        if (j - arr[i] >= 0 && dp[i][j - arr[i]] != max) {
          left = dp[i][j - arr[i]] + 1;
        }
        dp[i][j] = Math.min(left, dp[i - 1][j]);
      }
    }
    return dp[n - 1][aim] != max ? dp[n - 1][aim] : -1;
  }

  public static int minCoins2(int[] arr, int aim) {
    if (arr == null || arr.length == 0 || aim < 0) {
      return -1;
    }
    int n = arr.length;
    int max = Integer.MAX_VALUE;
    int[] dp = new int[aim + 1];
    for (int j = 1; j <= aim; j++) {
      dp[j] = max;
      if (j - arr[0] >= 0 && dp[j - arr[0]] != max) {
        dp[j] = dp[j - arr[0]] + 1;
      }
    }
    int left = 0;
    for (int i = 1; i < n; i++) {
      for (int j = 1; j <= aim; j++) {
        left = max;
        if (j - arr[i] >= 0 && dp[j - arr[i]] != max) {
          left = dp[j - arr[i]] + 1;
        }
        dp[j] = Math.min(left, dp[j]);
      }
    }
    return dp[aim] != max ? dp[aim] : -1;
  }

  public static int minCoins3(int[] arr, int aim) {
    if (arr == null || arr.length == 0 || aim < 0) {
      return -1;
    }
    int n = arr.length;
    int max = Integer.MAX_VALUE;
    int[][] dp = new int[n][aim + 1];
    for (int j = 1; j <= aim; j++) {
      dp[0][j] = max;
    }
    if (arr[0] <= aim) {
      dp[0][arr[0]] = 1;
    }
    int leftup = 0; // ���Ͻ�ĳ��λ�õ�ֵ
    for (int i = 1; i < n; i++) {
      for (int j = 1; j <= aim; j++) {
        leftup = max;
        if (j - arr[i] >= 0 && dp[i - 1][j - arr[i]] != max) {
          leftup = dp[i - 1][j - arr[i]] + 1;
        }
        dp[i][j] = Math.min(leftup, dp[i - 1][j]);
      }
    }
    return dp[n - 1][aim] != max ? dp[n - 1][aim] : -1;
  }

  public static int minCoins4(int[] arr, int aim) {
    if (arr == null || arr.length == 0 || aim < 0) {
      return -1;
    }
    int n = arr.length;
    int max = Integer.MAX_VALUE;
    int[] dp = new int[aim + 1];
    for (int j = 1; j <= aim; j++) {
      dp[j] = max;
    }
    if (arr[0] <= aim) {
      dp[arr[0]] = 1;
    }
    int leftup = 0; // ���Ͻ�ĳ��λ�õ�ֵ
    for (int i = 1; i < n; i++) {
      for (int j = aim; j > 0; j--) {
        leftup = max;
        if (j - arr[i] >= 0 && dp[j - arr[i]] != max) {
          leftup = dp[j - arr[i]] + 1;
        }
        dp[j] = Math.min(leftup, dp[j]);
      }
    }
    return dp[aim] != max ? dp[aim] : -1;
  }

  public static void main(String[] args) {
    int[] arr1 = {100, 20, 5, 10, 2, 50, 1};
    int aim1 = 17019;
    System.out.println(minCoins1(arr1, aim1));
    System.out.println(minCoins2(arr1, aim1));

    int[] arr2 = {10, 100, 2, 5, 5, 5, 10, 1, 1, 1, 2, 100};
    int aim2 = 223;
    System.out.println(minCoins3(arr2, aim2));
    System.out.println(minCoins4(arr2, aim2));

  }
}
