package com.lee.java8.time;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author bruce
 */
public class LocalDateToString {

  public static void main(String[] args) {
    //We will use current date from the system clock
    LocalDate today = LocalDate.now();
    System.out.println("Current date using default toString(same as ISO Standard Format): " + today);

    //Converting Date to a user specific format 1 - dd-MMM-yyyy
    DateTimeFormatter formatter_1 = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
    String format_1 = (today).format(formatter_1);
    System.out.println("Current date in format 'dd-MMM-yyyy': " + format_1);

    //Converting Date to a user specific format 2 - dd/MM/yyyy
    DateTimeFormatter formatter_2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    String format_2 = (today).format(formatter_2);
    System.out.println("Current date in format 'dd/MM/yyyy': " + format_2);

    //Converting Date to a user specific format 3 - E, dd MMM yyyy
    DateTimeFormatter formatter_3 = DateTimeFormatter.ofPattern("E, dd MMM yyyy");
    String format_3 = (today).format(formatter_3);
    System.out.println("Current date in format 'E, dd MMM yyyy': " + format_3);
  }
}
