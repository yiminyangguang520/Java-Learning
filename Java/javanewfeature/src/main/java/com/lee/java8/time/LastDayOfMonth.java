package com.lee.java8.time;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;

/**
 * @author bruce https://www.javabrahman.com/java-8/java-8-how-to-get-last-day-last-working-day-of-a-month-as-localdate/
 */
public class LastDayOfMonth {

  public static void main(String[] args) {
    //1A. Last day of current month
    LocalDate lastDayofCurrentMonth = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
    System.out.println("1A. Last day of the current month: " +
      lastDayofCurrentMonth.getDayOfWeek() + "," + lastDayofCurrentMonth);

    //1B. Last working day(LWD) of current month reusing lastDayOfCurrentMonth
    LocalDate lastWorkDayCurrentMonth = getLastWorkingDayOfMonth(lastDayofCurrentMonth);
    System.out.println("1B. Last working day of current month: " +
      lastWorkDayCurrentMonth.getDayOfWeek() + "," + lastWorkDayCurrentMonth);

    //2A. Last day of month for given date-"2017-01-13"
    LocalDate lastDayofMonthGivenDate = LocalDate.of(2017, 1, 13).with(TemporalAdjusters.lastDayOfMonth());
    System.out.println("2A. Last day of month for '2017-01-13': " +
      lastDayofMonthGivenDate.getDayOfWeek() + "," + lastDayofMonthGivenDate);

    //2B. LWD of month for date-"2017-01-13" reusing lastDayofMonthGivenDate
    LocalDate lastWorkDayGivenDate = getLastWorkingDayOfMonth(lastDayofMonthGivenDate);
    System.out.println("2B. Last working day of month for '2017-01-13': " +
      lastWorkDayGivenDate.getDayOfWeek() + "," + lastWorkDayGivenDate);

    //3A. Last day of month for year-month combination-"Apr, 2017"
    LocalDate lastDayofMonthYear = YearMonth.of(2017, 4).atEndOfMonth();
    System.out.println("3A. Last day of month for 'Apr, 2017': " +
      lastDayofMonthYear.getDayOfWeek() + "," + lastDayofMonthYear);

    //3B. LWD of month for year-month combination-"Apr, 2017" reusing lastDayofMonthYear
    LocalDate lastWorkDayMonthYear = getLastWorkingDayOfMonth(lastDayofMonthYear);
    System.out.println("3B. Last working day of month for 'Apr, 2017': " +
      lastWorkDayMonthYear.getDayOfWeek() + "," + lastWorkDayMonthYear);
  }

  /**
   * Method calculates last working day for last day of month as input
   *
   * @param lastDayOfMonth 某月最后一天
   * @return LocalDate instance containing last working day
   */
  public static LocalDate getLastWorkingDayOfMonth(LocalDate lastDayOfMonth) {
    LocalDate lastWorkingDayofMonth;
    switch (DayOfWeek.of(lastDayOfMonth.get(ChronoField.DAY_OF_WEEK))) {
      case SATURDAY:
        lastWorkingDayofMonth = lastDayOfMonth.minusDays(1);
        break;
      case SUNDAY:
        lastWorkingDayofMonth = lastDayOfMonth.minusDays(2);
        break;
      default:
        lastWorkingDayofMonth = lastDayOfMonth;
    }
    return lastWorkingDayofMonth;
  }
}
