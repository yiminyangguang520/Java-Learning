package com.logicbig.example.model;

/**
 * @author litz-a
 */
public class OrderItem {

  private int id;
  private String item;
  private int quantity;
  private String customer;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getItem() {
    return item;
  }

  public void setItem(String item) {
    this.item = item;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public String getCustomer() {
    return customer;
  }

  public void setCustomer(String customer) {
    this.customer = customer;
  }

  @Override
  public String toString() {
    return "OrderItem{" +
        "id=" + id +
        ", item='" + item + '\'' +
        ", quantity=" + quantity +
        ", customer='" + customer + '\'' +
        '}';
  }
}