package chapter_5_stringproblem;


public class Problem_06_ReplaceString {

  public static String replace(String str, String from, String to) {
    if (str == null || from == null || str.equals("") || from.equals("")) {
      return str;
    }
    char[] chas = str.toCharArray();
    char[] chaf = from.toCharArray();
    int match = 0;
    for (int i = 0; i < chas.length; i++) {
      if (chas[i] == chaf[match++]) {
        if (match == chaf.length) {
          clear(chas, i, chaf.length);
          match = 0;
        }
      } else {
        if (chas[i] == chaf[0]) {
          i--;
        }
        match = 0;
      }
    }
    String res = "";
    String cur = "";
    for (int i = 0; i < chas.length; i++) {
      if (chas[i] != 0) {
        cur = cur + String.valueOf(chas[i]);
      }
      if (chas[i] == 0 && (i == 0 || chas[i - 1] != 0)) {
        res = res + cur + to;
        cur = "";
      }
    }
    if (!cur.equals("")) {
      res = res + cur;
    }
    return res;
  }

  public static void clear(char[] chas, int end, int len) {
    while (len-- != 0) {
      chas[end--] = 0;
    }
  }

  public static void main(String[] args) {
    String str = "abc1abcabc1234abcabcabc5678";
    String from = "abc";
    String to = "XXXXX";
    System.out.println(replace(str, from, to));

    str = "abc";
    from = "123";
    to = "X";
    System.out.println(replace(str, from, to));

  }

}
