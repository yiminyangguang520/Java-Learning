package com.lee.java8;

import com.lee.java8.model.Car;
import com.lee.java8.model.Insurance;
import com.lee.java8.model.Person;
import java.util.Optional;

/***************************************
 * @author Alex Wang
 * @date 2016/10/25 QQ:532500648
 * QQ交流群:286081824
 ***************************************/
public class OptionalInAction {

  public static void main(String[] args) {
    Optional.ofNullable(getInsuranceNameByOptional(null)).ifPresent(System.out::println);
  }

  private static String getInsuranceNameByOptional(Person person) {

    return Optional.ofNullable(person)
        .flatMap(Person::getCar).flatMap(Car::getInsurance)
        .map(Insurance::getName).orElse("Unknown");
  }
}
