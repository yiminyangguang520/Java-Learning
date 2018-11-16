package com.mytechtip.mongodb.bigdecimal.model;

import java.math.BigDecimal;
import java.util.Date;
import org.springframework.data.annotation.Id;

/**
 * @author cwang
 */
public class Product {

  @Id
  private String id;

  private String name;

  private BigDecimal price;

  private Date date;

  public Product() {

  }

  public Product(String name, BigDecimal price) {
    this.name = name;
    this.price = price;
  }

  public Product(String name, BigDecimal price, Date date) {
    this.name = name;
    this.price = price;
    this.date = date;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  @Override
  public String toString() {
    return "Product{" + "id=" + id + ", name=" + name + ", price=" + price + '}';
  }


}
