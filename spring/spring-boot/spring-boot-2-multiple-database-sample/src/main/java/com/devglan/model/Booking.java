package com.devglan.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author litz-a
 */
@Entity
@Table
public class Booking {

  @Id
  @Column
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long createdBy;
  private String pickupAddress;
  private String dropAddress;
  private String bookingAmount;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(Long createdBy) {
    this.createdBy = createdBy;
  }

  public String getPickupAddress() {
    return pickupAddress;
  }

  public void setPickupAddress(String pickupAddress) {
    this.pickupAddress = pickupAddress;
  }

  public String getDropAddress() {
    return dropAddress;
  }

  public void setDropAddress(String dropAddress) {
    this.dropAddress = dropAddress;
  }

  public String getBookingAmount() {
    return bookingAmount;
  }

  public void setBookingAmount(String bookingAmount) {
    this.bookingAmount = bookingAmount;
  }

}
