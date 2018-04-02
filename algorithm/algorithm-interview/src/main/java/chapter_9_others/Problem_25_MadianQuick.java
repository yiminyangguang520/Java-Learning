package chapter_9_others;

import java.util.Arrays;
import java.util.Comparator;

public class Problem_25_MadianQuick {

  public static class MedianHolder {

    private MyHeap<Integer> minHeap;
    private MyHeap<Integer> maxHeap;

    public MedianHolder() {
      this.minHeap = new MyHeap<Integer>(new MinHeapComparator());
      this.maxHeap = new MyHeap<Integer>(new MaxHeapComparator());
    }

    public void addNumber(Integer num) {
      if (this.maxHeap.isEmpty()) {
        this.maxHeap.add(num);
        return;
      }
      if (this.maxHeap.getHead() >= num) {
        this.maxHeap.add(num);
      } else {
        if (this.minHeap.isEmpty()) {
          this.minHeap.add(num);
          return;
        }
        if (this.minHeap.getHead() > num) {
          this.maxHeap.add(num);
        } else {
          this.minHeap.add(num);
        }
      }
      this.modifyTwoHeapsSize();
    }

    public Integer getMedian() {
      long maxHeapSize = this.maxHeap.getSize();
      long minHeapSize = this.minHeap.getSize();
      if (maxHeapSize + minHeapSize == 0) {
        return null;
      }
      Integer maxHeapHead = this.maxHeap.getHead();
      Integer minHeapHead = this.minHeap.getHead();
      if (((maxHeapSize + minHeapSize) & 1) == 0) {
        return (maxHeapHead + minHeapHead) / 2;
      } else if (maxHeapSize > minHeapSize) {
        return maxHeapHead;
      } else {
        return minHeapHead;
      }
    }

    private void modifyTwoHeapsSize() {
      if (this.maxHeap.getSize() == this.minHeap.getSize() + 2) {
        this.minHeap.add(this.maxHeap.popHead());
      }
      if (this.minHeap.getSize() == this.maxHeap.getSize() + 2) {
        this.maxHeap.add(this.minHeap.popHead());
      }
    }

  }

  //���ɴ���ѵıȽ���
  public static class MaxHeapComparator implements Comparator<Integer> {

    @Override
    public int compare(Integer o1, Integer o2) {
      if (o2 > o1) {
        return 1;
      } else {
        return -1;
      }
    }
  }

  //����С���ѵıȽ���
  public static class MinHeapComparator implements Comparator<Integer> {

    @Override
    public int compare(Integer o1, Integer o2) {
      if (o2 < o1) {
        return 1;
      } else {
        return -1;
      }
    }
  }

  // binary tree node contains parent reference
  public static class Node<K> {

    public K value;
    public Node<K> left;
    public Node<K> right;
    public Node<K> parent;

    public Node(K data) {
      value = data;
    }
  }

  public static class MyHeap<K> {

    private Node<K> head; // heap head node
    private Node<K> last; // heap last node
    private long size; // heap size
    private Comparator<K> comp; // max-heap or min-heap

    public MyHeap(Comparator<K> compare) {
      head = null;
      last = null;
      size = 0;
      comp = compare; // max-heap or min-heap based on your Comparator
    }

    public K getHead() {
      return head == null ? null : head.value;
    }

    public long getSize() {
      return size;
    }

    public boolean isEmpty() {
      return size == 0 ? true : false;
    }

    // add a new node to heap
    public void add(K value) {
      Node<K> newNode = new Node<K>(value);
      if (size == 0) {
        head = newNode;
        last = newNode;
        size++;
        return;
      }
      Node<K> node = last;
      Node<K> parent = node.parent;
      // find right position to insert new node
      while (parent != null && node != parent.left) {
        node = parent;
        parent = node.parent;
      }
      Node<K> nodeToAdd = null;
      if (parent == null) {
        nodeToAdd = mostLeft(head);
        nodeToAdd.left = newNode;
        newNode.parent = nodeToAdd;
      } else if (parent.right == null) {
        parent.right = newNode;
        newNode.parent = parent;
      } else {
        nodeToAdd = mostLeft(parent.right);
        nodeToAdd.left = newNode;
        newNode.parent = nodeToAdd;
      }
      last = newNode;
      // heap insertion modify
      heapInsertModify();
      size++;
    }

    public K popHead() {
      if (size == 0) {
        return null;
      }
      Node<K> res = head;
      if (size == 1) {
        head = null;
        last = null;
        size--;
        return res.value;
      }
      Node<K> oldLast = popLastAndSetPreviousLast();
      // if after pop last node, the size of heap is 1
      if (size == 1) {
        head = oldLast;
        last = oldLast;
        return res.value;
      }
      // if after pop last node, the size of heap is larger than 1
      Node<K> headLeft = res.left;
      Node<K> headRight = res.right;
      oldLast.left = headLeft;
      if (headLeft != null) {
        headLeft.parent = oldLast;
      }
      oldLast.right = headRight;
      if (headRight != null) {
        headRight.parent = oldLast;
      }
      res.left = null;
      res.right = null;
      head = oldLast;
      // heap heapify process
      heapify(oldLast);
      return res.value;
    }

    // find the most left node of subtree which node is head
    private Node<K> mostLeft(Node<K> node) {
      while (node.left != null) {
        node = node.left;
      }
      return node;
    }

    // find the most right node of subtree which node is head
    private Node<K> mostRight(Node<K> node) {
      while (node.right != null) {
        node = node.right;
      }
      return node;
    }

    // heap insertion process
    private void heapInsertModify() {
      Node<K> node = last;
      Node<K> parent = node.parent;
      if (parent != null && comp.compare(node.value, parent.value) < 0) {
        last = parent;
      }
      while (parent != null && comp.compare(node.value, parent.value) < 0) {
        swapClosedTwoNodes(node, parent);
        parent = node.parent;
      }
      if (head.parent != null) {
        head = head.parent;
      }
    }

    // heap heapify process
    private void heapify(Node<K> node) {
      Node<K> left = node.left;
      Node<K> right = node.right;
      Node<K> most = node;
      while (left != null) {
        if (left != null && comp.compare(left.value, most.value) < 0) {
          most = left;
        }
        if (right != null && comp.compare(right.value, most.value) < 0) {
          most = right;
        }
        if (most != node) {
          swapClosedTwoNodes(most, node);
        } else {
          break;
        }
        left = node.left;
        right = node.right;
        most = node;
      }
      if (node.parent == last) {
        last = node;
      }
      while (node.parent != null) {
        node = node.parent;
      }
      head = node;
    }

    // swap two nodes in tree, and those two nodes are closed
    private void swapClosedTwoNodes(Node<K> node, Node<K> parent) {
      if (node == null || parent == null) {
        return;
      }
      Node<K> parentParent = parent.parent;
      Node<K> parentLeft = parent.left;
      Node<K> parentRight = parent.right;
      Node<K> nodeLeft = node.left;
      Node<K> nodeRight = node.right;
      node.parent = parentParent;
      if (parentParent != null) {
        if (parent == parentParent.left) {
          parentParent.left = node;
        } else {
          parentParent.right = node;
        }
      }
      parent.parent = node;
      if (nodeLeft != null) {
        nodeLeft.parent = parent;
      }
      if (nodeRight != null) {
        nodeRight.parent = parent;
      }
      if (node == parent.left) {
        node.left = parent;
        node.right = parentRight;
        if (parentRight != null) {
          parentRight.parent = node;
        }
      } else {
        node.left = parentLeft;
        node.right = parent;
        if (parentLeft != null) {
          parentLeft.parent = node;
        }
      }
      parent.left = nodeLeft;
      parent.right = nodeRight;
    }

    // pop last node in Tree, and set last to previous last node
    private Node<K> popLastAndSetPreviousLast() {
      Node<K> node = last;
      Node<K> parent = node.parent;
      while (parent != null && node != parent.right) {
        node = parent;
        parent = node.parent;
      }
      if (parent == null) {
        node = last;
        parent = node.parent;
        node.parent = null;
        if (node == parent.left) {
          parent.left = null;
        } else {
          parent.right = null;
        }
        last = mostRight(head);
      } else {
        Node<K> newLast = mostRight(parent.left);
        node = last;
        parent = node.parent;
        node.parent = null;
        if (node == parent.left) {
          parent.left = null;
        } else {
          parent.right = null;
        }
        last = newLast;
      }
      size--;
      return node;
    }

  }

  // for test
  public static int[] getRandomArray(int maxLen, int maxValue) {
    int[] res = new int[(int) (Math.random() * maxLen) + 1];
    for (int i = 0; i != res.length; i++) {
      res[i] = (int) (Math.random() * maxValue);
    }
    return res;
  }

  // for test, this method is ineffective but absolutely right
  public static int getMedianOfArray(int[] arr) {
    int[] newArr = Arrays.copyOf(arr, arr.length);
    Arrays.sort(newArr);
    int mid = (newArr.length - 1) / 2;
    if ((newArr.length & 1) == 0) {
      return (newArr[mid] + newArr[mid + 1]) / 2;
    } else {
      return newArr[mid];
    }
  }

  public static void printArray(int[] arr) {
    for (int i = 0; i != arr.length; i++) {
      System.out.print(arr[i] + " ");
    }
    System.out.println();
  }

  public static void main(String[] args) {
    boolean err = false;
    int testTimes = 200000;
    for (int i = 0; i != testTimes; i++) {
      int len = 30;
      int maxValue = 1000;
      int[] arr = getRandomArray(len, maxValue);
      MedianHolder medianHold = new MedianHolder();
      for (int j = 0; j != arr.length; j++) {
        medianHold.addNumber(arr[j]);
      }
      if (medianHold.getMedian() != getMedianOfArray(arr)) {
        err = true;
        printArray(arr);
        break;
      }
    }
    System.out.println(err ? "Oops..what a fuck!"
        : "today is a beautiful day^_^");

  }

}
