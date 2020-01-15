package com.lee.java8.time;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author bruce
 */
public class StringToLocalDate {

  public static void main(String[] args) {
    //Converting String in format 'dd-MMM-yyyy' to LocalDate
    String dateStr_1 = "28-九月-2016";
    DateTimeFormatter formatter_1 = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
    LocalDate localDate_1 = LocalDate.parse(dateStr_1, formatter_1);
    System.out.println("Input String with value: " + dateStr_1);
    System.out.println("Converted Date in default ISO format: " + localDate_1 + "\n");

    //Converting String in format 'EEEE, MMM d yyyy' to LocalDate
    String dateStr_2 = "星期三, 九月 28 2016";
    DateTimeFormatter formatter_2 = DateTimeFormatter.ofPattern("E, MMM dd yyyy");
    LocalDate localDate_2 = LocalDate.parse(dateStr_2, formatter_2);
    System.out.println("Input String with value: " + dateStr_2);
    System.out.println("Converted Date in default ISO format: " + localDate_2 + "\n");

    //Converting String in format 'dd/MM/YY' to LocalDate
    String dateStr_3 = "28/09/16";
    DateTimeFormatter formatter_3 = DateTimeFormatter.ofPattern("dd/MM/yy");
    LocalDate localDate_3 = LocalDate.parse(dateStr_3, formatter_3);
    System.out.println("Input String with value: " + dateStr_3);
    System.out.println("Converted Date in default ISO format: " + localDate_3);
  }
}
