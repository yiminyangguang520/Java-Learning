package com.javasampleapproach.couchbase.model;

import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;

/**
 * @author min
 */
@Document
public class Customer {

  @Id
  private String id;

  @Field
  private String firstName;

  @Field
  private String lastName;

  public Customer(String id, String firstName, String lastName) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @Override
  public String toString() {
    return String.format("Customer[ id=%s, firstName=%s, lastName=%s]", this.id, this.firstName, this.lastName);
  }
}
