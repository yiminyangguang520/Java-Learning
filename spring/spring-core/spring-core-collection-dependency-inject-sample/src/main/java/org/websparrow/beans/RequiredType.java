package org.websparrow.beans;

import java.util.Hashtable;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

/**
 * @author litz-a
 */
public class RequiredType {

  private Vector<String> empName;
  private TreeSet<String> empId;
  private Hashtable<String, String> empIdName;

  public void setEmpName(Vector<String> empName) {
    this.empName = empName;
  }

  public void setEmpId(TreeSet<String> empId) {
    this.empId = empId;
  }

  public void setEmpIdName(Hashtable<String, String> empIdName) {
    this.empIdName = empIdName;
  }

  public void display() {

    System.out.println("Name......" + "");

    for (String name : empName) {
      System.out.println(name);
    }

    System.out.println("\nIds......" + "");

    for (String id : empId) {
      System.out.println(id);
    }

    System.out.println("\nId and Name...." + "");

    Set<String> set = empIdName.keySet();
    for (String idName : set) {
      System.out.println(idName + " : " + empIdName.get(idName));
    }
  }
}
