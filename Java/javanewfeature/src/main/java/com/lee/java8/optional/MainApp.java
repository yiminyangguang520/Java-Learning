package com.lee.java8.optional;

import java.util.Optional;

/**
 * @author litz-a
 */
public class MainApp {

  public static void main(String[] args) {

    String text = null;

    // Optional<String> opText = Optional.empty();

    // If soundcard were null, a NullPointerException would be immediately
    // thrown (rather than getting a latent error once you try to access
    // properties of the soundcard).

    // Optional<String> opText1 = Optional.of(text);

    Optional<String> opText1 = Optional.ofNullable(text);
    // opText1.orElseThrow(IllegalStateException::new);
    System.out.println(opText1.orElse("this is a null String"));

    text = "Java Sample Approach";
    Optional<String> opText2 = Optional.ofNullable(text);
    opText2.ifPresent(s -> System.out.println("this is NOT null value: " + s));

    Address address = new Address("USA");
    Optional<Address> opAddress = Optional.ofNullable(address);
    opAddress.map(Address::getLocation).filter(location -> location.contains("US"))
        .ifPresent(s -> System.out.println("Address contains \"US\": " + s));

    Address address1 = null;
    Optional<Address> opAddress1 = Optional.ofNullable(address1);

    Address address2 = opAddress1.orElseGet(() -> {
      System.out.println("System: address with null location will be replace with default value");
      return new Address("default location for null Address");
    });
    System.out.println("Location: " + address2.getLocation());

    Customer customer = new Customer("Jack");
    // customer.setAddress(Optional.of(new Address("US")));

    Optional<Customer> opCustomer = Optional.ofNullable(customer);

    String location = opCustomer.flatMap(Customer::getAddress).map(Address::getLocation)
        .orElse("location not detected");

    System.out.println(location);
  }
}
