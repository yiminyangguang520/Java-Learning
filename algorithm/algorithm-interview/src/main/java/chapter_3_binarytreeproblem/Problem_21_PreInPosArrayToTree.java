package chapter_3_binarytreeproblem;

import java.util.HashMap;

public class Problem_21_PreInPosArrayToTree {

  public static class Node {

    public int value;
    public Node left;
    public Node right;

    public Node(int data) {
      this.value = data;
    }
  }

  public static Node preInToTree(int[] pre, int[] in) {
    if (pre == null || in == null) {
      return null;
    }
    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    for (int i = 0; i < in.length; i++) {
      map.put(in[i], i);
    }
    return preIn(pre, 0, pre.length - 1, in, 0, in.length - 1, map);
  }

  public static Node preIn(int[] p, int pi, int pj, int[] n, int ni, int nj,
      HashMap<Integer, Integer> map) {
    if (pi > pj) {
      return null;
    }
    Node head = new Node(p[pi]);
    int index = map.get(p[pi]);
    head.left = preIn(p, pi + 1, pi + index - ni, n, ni, index - 1, map);
    head.right = preIn(p, pi + index - ni + 1, pj, n, index + 1, nj, map);
    return head;
  }

  public static Node inPosToTree(int[] in, int[] pos) {
    if (in == null || pos == null) {
      return null;
    }
    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    for (int i = 0; i < in.length; i++) {
      map.put(in[i], i);
    }
    return inPos(in, 0, in.length - 1, pos, 0, pos.length - 1, map);
  }

  public static Node inPos(int[] n, int ni, int nj, int[] s, int si, int sj,
      HashMap<Integer, Integer> map) {
    if (si > sj) {
      return null;
    }
    Node head = new Node(s[sj]);
    int index = map.get(s[sj]);
    head.left = inPos(n, ni, index - 1, s, si, si + index - ni - 1, map);
    head.right = inPos(n, index + 1, nj, s, si + index - ni, sj - 1, map);
    return head;
  }

  // ÿ���ڵ�ĺ�������Ϊ0��2�Ķ��������ܱ�����������ع�����
  public static Node prePosToTree(int[] pre, int[] pos) {
    if (pre == null || pos == null) {
      return null;
    }
    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    for (int i = 0; i < pos.length; i++) {
      map.put(pos[i], i);
    }
    return prePos(pre, 0, pre.length - 1, pos, 0, pos.length - 1, map);
  }

  public static Node prePos(int[] p, int pi, int pj, int[] s, int si, int sj,
      HashMap<Integer, Integer> map) {
    Node head = new Node(s[sj--]);
    if (pi == pj) {
      return head;
    }
    int index = map.get(p[++pi]);
    head.left = prePos(p, pi, pi + index - si, s, si, index, map);
    head.right = prePos(p, pi + index - si + 1, pj, s, index + 1, sj, map);
    return head;
  }

  // for test -- print tree
  public static void printTree(Node head) {
    System.out.println("Binary Tree:");
    printInOrder(head, 0, "H", 17);
    System.out.println();
  }

  public static void printInOrder(Node head, int height, String to, int len) {
    if (head == null) {
      return;
    }
    printInOrder(head.right, height + 1, "v", len);
    String val = to + head.value + to;
    int lenM = val.length();
    int lenL = (len - lenM) / 2;
    int lenR = len - lenM - lenL;
    val = getSpace(lenL) + val + getSpace(lenR);
    System.out.println(getSpace(height * len) + val);
    printInOrder(head.left, height + 1, "^", len);
  }

  public static String getSpace(int num) {
    String space = " ";
    StringBuffer buf = new StringBuffer("");
    for (int i = 0; i < num; i++) {
      buf.append(space);
    }
    return buf.toString();
  }

  public static void main(String[] args) {
    int[] pre = {1, 2, 4, 5, 8, 9, 3, 6, 7};
    int[] in = {4, 2, 8, 5, 9, 1, 6, 3, 7};
    int[] pos = {4, 8, 9, 5, 2, 6, 7, 3, 1};
    printTree(preInToTree(pre, in));
    printTree(inPosToTree(in, pos));
    printTree(prePosToTree(pre, pos));

  }

}
