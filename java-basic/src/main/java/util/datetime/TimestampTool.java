package util.datetime;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by eme on 2017/3/20.
 *
 * @author Bruce Lee
 * @date 2017-03-20
 */
public class TimestampTool {

  /**
   * 将毫秒转成时间
   */
  public static String getTimeMillisToDate(long l) {
    Timestamp d = new Timestamp(l);
    return d.toString().substring(0, 19);
  }

  /**
   * 当前时间
   *
   * @return Timestamp
   */
  public static Timestamp crunttime() {
    return new Timestamp(System.currentTimeMillis());
  }

  /**
   * 获取当前时间的字符串
   *
   * @return String ex:2006-07-07
   */
  public static String getCurrentDate() {
    Timestamp d = crunttime();
    return d.toString().substring(0, 10);
  }

  /**
   * 获取当前时间的字符串
   *
   * @return String ex:2006-07-07 22:10:10
   */
  public static String getCurrentDateTime() {
    Timestamp d = crunttime();
    return d.toString().substring(0, 19);
  }

  public static String getWeekDay() {
    Calendar date = Calendar.getInstance();
    date.setTime(crunttime());
    return new SimpleDateFormat("EEEE").format(date.getTime());
  }

  /**
   * 获取指定时间的字符串,只到日期
   *
   * @param t Timestamp
   * @return String ex:2006-07-07
   */
  public static String getStrDate(Timestamp t) {
    return t.toString().substring(0, 10);
  }

  /**
   * 获取指定时间的字符串
   *
   * @param t Timestamp
   * @return String ex:2006-07-07 22:10:10
   */
  public static String getStrDateTime(Timestamp t) {
    return t.toString().substring(0, 19);
  }

  /**
   * 获得当前日期的前段日期
   *
   * @return String
   */
  public static String getStrIntervalDate(String days) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Calendar cal = Calendar.getInstance();
    cal.add(Calendar.DATE, -Integer.parseInt(days));
    String strBeforeDays = sdf.format(cal.getTime());
    return strBeforeDays;
  }

  /**
   * 格式化时间
   *
   * @param dt String -> yyyy-MM-dd hh:mm:ss
   * @return java.util.Date.Date -> yyyy-MM-dd hh:mm:ss
   */
  public static Date parseDateTime(String dt) {
    Date jDt = new Date();
    try {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      if (dt.length() > 10) {
        jDt = sdf.parse(dt);
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return jDt;
  }

  /**
   * 格式化时间yyyy-MM-dd HH:mm:ss
   *
   * @param date java.util.Date
   * @return String -> yyyy-MM-dd HH:mm:ss
   */
  public static String parseDateTime(Date date) {
    String s = null;
    if (date != null) {
      try {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        s = f.format(date);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return s;
  }

  /**
   * 格式化日期
   *
   * @param dt String -> yyyy-MM-dd
   * @return java.util.Date.Date -> yyyy-MM-dd
   */
  public static Date parseDate(String dt) {
    Date jDt = new Date();
    try {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      if (dt.length() >= 8) {
        jDt = sdf.parse(dt);
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return jDt;
  }

  /**
   * 格式化时间yyyy-MM-dd
   *
   * @param date java.util.Date
   * @return String -> yyyy-MM-dd
   */
  public static String parseDate(Date date) {
    String s = null;
    try {
      if (date != null) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        s = f.format(date);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return s;
  }

  /**
   * @return String
   */
  public static String getLongDateFromShortDate(String dt) {
    String strDT = dt;
    try {
      if (strDT != null && strDT.length() <= 10) {
        strDT = dt.trim() + " 00:00:00";
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return strDT;
  }

  /**
   * @return String
   */
  public static String getShortDateToHHMM(String dt) {
    String jDt = dt;
    try {
      if (jDt != null && jDt.length() <= 10) {
        jDt = jDt + " 00:00";
      }
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
      Date date = sdf.parse(jDt);
      jDt = sdf.format(date);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return jDt;
  }

  /**
   * @return String
   */
  public static String formatDateToHHMM(String dateStr) {
    String resultDate = null;
    try {
      if (dateStr.length() > 10) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:ss");
        Date date = sdf.parse(dateStr);
        resultDate = sdf.format(date);
      } else {
        resultDate = dateStr;
      }
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return resultDate;
  }

  /**
   * 返回日期 格式:2006-07-05
   *
   * @return Timestamp
   */
  public static Timestamp date(String str) {
    Timestamp tp = null;
    if (str.length() <= 10) {
      String[] string = str.trim().split("-");
      int one = Integer.parseInt(string[0]) - 1900;
      int two = Integer.parseInt(string[1]) - 1;
      int three = Integer.parseInt(string[2]);
      tp = new Timestamp(one, two, three, 0, 0, 0, 0);
    }
    return tp;
  }

  /**
   * 获取指定日期之后的日期字符串 如 2007-04-15 后一天 就是 2007-04-16
   * @param strDate
   * @param day
   * @return
   */
  public static String getNextDay(String strDate, int day) {
    if (strDate != null && !strDate.equals("")) {
      Calendar cal1 = Calendar.getInstance();
      String[] string = strDate.trim().split("-");
      int one = Integer.parseInt(string[0]) - 1900;
      int two = Integer.parseInt(string[1]) - 1;
      int three = Integer.parseInt(string[2]);
      cal1.setTime(new Date(one, two, three));
      cal1.add(Calendar.DAY_OF_MONTH, day);
      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
      return formatter.format(cal1.getTime());
    } else {
      return null;
    }
  }

  /**
   * 获取指定日期之后的日期字符串 如 2007-02-28 后一年 就是 2008-02-29 （含闰年）
   * @param strDate
   * @param year
   * @return
   */
  public static String getNextYear(String strDate, int year) {
    Calendar cal1 = Calendar.getInstance();
    String[] string = strDate.trim().split("-");
    int one = Integer.parseInt(string[0]) - 1900;
    int two = Integer.parseInt(string[1]) - 1;
    int three = Integer.parseInt(string[2]);
    cal1.setTime(new Date(one, two, three));
    cal1.add(Calendar.YEAR, year);
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    return formatter.format(cal1.getTime());
  }

  /**
   * 返回时间和日期 格式:2006-07-05 22:10:10
   *
   * @return Timestamp
   */
  public static Timestamp datetime(String str) {
    Timestamp tp = null;
    if (str != null && str.length() > 10) {
      String[] string = str.trim().split(" ");
      String[] date = string[0].split("-");
      String[] time = string[1].split(":");
      int date1 = Integer.parseInt(date[0]) - 1900;
      int date2 = Integer.parseInt(date[1]) - 1;
      int date3 = Integer.parseInt(date[2]);
      int time1 = Integer.parseInt(time[0]);
      int time2 = Integer.parseInt(time[1]);
      int time3 = Integer.parseInt(time[2]);
      tp = new Timestamp(date1, date2, date3, time1, time2, time3, 0);
    }
    return tp;
  }

  /**
   * 返回日期和时间(没有秒) 格式:2006-07-05 22:10
   *
   * @return Timestamp
   */
  public static Timestamp datetimeHm(String str) {
    Timestamp tp = null;
    if (str.length() > 10) {
      String[] string = str.trim().split(" ");
      String[] date = string[0].split("-");
      String[] time = string[1].split(":");
      int date1 = Integer.parseInt(date[0]) - 1900;
      int date2 = Integer.parseInt(date[1]) - 1;
      int date3 = Integer.parseInt(date[2]);
      int time1 = Integer.parseInt(time[0]);
      int time2 = Integer.parseInt(time[1]);
      tp = new Timestamp(date1, date2, date3, time1, time2, 0, 0);
    }
    return tp;
  }

  /**
   * 获得当前系统日期与本周一相差的天数
   *
   * @return int
   */
  private static int getMondayPlus() {
    Calendar calendar = Calendar.getInstance();
    // 获得今天是一周的第几天，正常顺序是星期日是第一天，星期一是第二天......
    // 星期日是第一天
    int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
    return (dayOfWeek == 1) ? -6 : 2 - dayOfWeek;
  }

  /**
   * 获得距当前时间所在某星期的周一的日期 例： 0-本周周一日期 -1-上周周一日期 1-下周周一日期
   *
   * @param week int
   * @return java.util.Date
   */
  public static Date getMondayOfWeek(int week) {
    // 相距周一的天数差
    int mondayPlus = getMondayPlus();
    GregorianCalendar current = new GregorianCalendar();
    current.add(GregorianCalendar.DATE, mondayPlus + 7 * week);
    return current.getTime();
  }

  /**
   * 获得某日前后的某一天
   *
   * @param date java.util.Date
   * @param day int
   * @return java.util.Date
   */
  public static Date getDay(Date date, int day) {
    GregorianCalendar c = new GregorianCalendar();
    c.setTime(date);
    c.add(GregorianCalendar.DATE, day);
    return c.getTime();
  }

  /**
   * 获得距当前周的前后某一周的日期
   *
   * @param week int
   * @return String[]
   */
  public static String[] getDaysOfWeek(int week) {
    String[] days = new String[7];
    // 获得距本周前或后的某周周一
    Date monday = getMondayOfWeek(week);
    Timestamp t = new Timestamp(monday.getTime());
    days[0] = getStrDate(t);
    for (int i = 1; i < 7; i++) {
      t = new Timestamp(getDay(monday, i).getTime());
      days[i] = getStrDate(t);
    }
    return days;
  }

  /***
   * MCC的UTC时间转换，MCC的UTC不是到毫秒的
   *
   * @param utc
   * @return java.util.Date
   */
  public static Date mccUTC2Date(long utc) {
    Date d = new Date();
    // 转成毫秒
    d.setTime(utc * 1000);
    return d;
  }

  /**
   * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
   * @param strDate
   * @return
   */
  public static Date strToDateLong(String strDate) {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    ParsePosition pos = new ParsePosition(0);
    Date strtodate = (Date) formatter.parse(strDate, pos);
    if (strtodate == null) {
      formatter = new SimpleDateFormat("yyyy-MM-dd");
      strtodate = (Date) formatter.parse(strDate, pos);
    }
    return strtodate;
  }

  /**
   * 将 yyyy-MM-dd HH:mm 格式字符串转换为时间
   * @param strDate
   * @return
   */
  public static Date strToDateTime(String strDate) {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    ParsePosition pos = new ParsePosition(0);
    Date strtodate = (Date) formatter.parse(strDate, pos);
    if (strtodate == null) {
      formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      strtodate = (Date) formatter.parse(strDate, pos);
    }
    return strtodate;
  }

  /**
   * 根据输入的字符串返回日期字符串 2006-07-07 22:10 2006-07-07
   * @param str
   * @return
   */
  public static String getStrDate(String str) {
    if (str.length() > 10) {
      String[] string = str.trim().split(" ");
      return string[0];
    } else {
      return getCurrentDate();
    }
  }

  /**
   * 获取当前时间的字符串 2006-07-07 22:10:10 2006-07-07_221010
   * @return
   */
  public static String getStrDateTime() {
    Timestamp d = crunttime();
    return d.toString().substring(0, 19).replace(":", "").replace(" ", "_");
  }

  /**
   * 根据日期字符串，返回今天，昨天或日期
   * @param str
   * @return
   */
  public static String getDayOrDate(String str) {
    if (str != null && !str.equals("")) {
      if (getNextDay(str, 0).equals(getCurrentDate())) {
        str = "今天";
      } else if (getNextDay(str, 1).equals(getCurrentDate())) {
        str = "昨天";
      } else if (getNextDay(str, 2).equals(getCurrentDate())) {
        str = "前天";
      }
    }
    return str;
  }

  /**
   * 返回当前日期所在星期，2对应星期一
   * @return
   */
  public static int getMonOfWeek() {
    Calendar cal1 = Calendar.getInstance();
    cal1.setTime(new Date());
    return cal1.get(Calendar.DAY_OF_WEEK);
  }

  public static void main(String[] args) {
    System.out.println(System.currentTimeMillis());
  }

  /**
   * 获取当前日期之前的日期字符串 如 2007-04-15 前5月 就是 2006-11-15
   */
  public static String getPreviousMonth(int month) {
    Calendar cal1 = Calendar.getInstance();
    cal1.setTime(new Date());
    cal1.add(Calendar.MONTH, -month);
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    return formatter.format(cal1.getTime());

  }

  public static String getStrYear(int year) {
    Calendar cal1 = Calendar.getInstance();
    cal1.setTime(new Date());
    cal1.add(Calendar.YEAR, -year);
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
    return formatter.format(cal1.getTime()) + "年份";
  }

  /**
   * 比较两个日期前后 可以大于或等于
   */
  public static boolean compareTwoDays(String starDate, String endDate) {
    Calendar cal_start = Calendar.getInstance();
    Calendar cal_end = Calendar.getInstance();
    cal_start.setTime(parseDate(starDate));
    cal_end.setTime(parseDate(endDate));
    return cal_end.after(cal_start);
  }

  public static int getDaysBetween(java.util.Calendar d1,
      java.util.Calendar d2) {
    // swap dates so that d1 is start and d2 is end
    if (d1.after(d2)) {
      java.util.Calendar swap = d1;
      d1 = d2;
      d2 = swap;
    }
    int days = d2.get(java.util.Calendar.DAY_OF_YEAR)
        - d1.get(java.util.Calendar.DAY_OF_YEAR);
    int y2 = d2.get(java.util.Calendar.YEAR);
    if (d1.get(java.util.Calendar.YEAR) != y2) {
      d1 = (java.util.Calendar) d1.clone();
      do {
        days += d1.getActualMaximum(java.util.Calendar.DAY_OF_YEAR);
        d1.add(java.util.Calendar.YEAR, 1);
      } while (d1.get(java.util.Calendar.YEAR) != y2);
    }
    return days;
  }

  /**
   * 得到两个日期之间的年
   * @param starDate
   * @param endDate
   * @return 日期之间的年
   */
  public static int dateDiffYear(String starDate, String endDate) {
    int result = 0;
    Calendar d1 = Calendar.getInstance();
    Calendar d2 = Calendar.getInstance();
    d1.setTime(parseDate(starDate));
    d2.setTime(parseDate(endDate));

    // 日期大小翻转
    // swap dates so that d1 is start and d2 is end
    if (d1.after(d2)) {
      java.util.Calendar swap = d1;
      d1 = d2;
      d2 = swap;
    }
    int yy = d2.get(Calendar.YEAR) - d1.get(Calendar.YEAR);
    int mm = d2.get(Calendar.MONTH) - d1.get(Calendar.MONTH);
    if (mm < 0) {
      result = yy - 1;
    }
    if (mm > 0) {
      result = yy;
    }
    if (mm == 0) {
      if ((d2.getTimeInMillis() - d1.getTimeInMillis()) >= 0) {
        result = yy;
      } else {
        result = yy - 1;
      }
    }
    return result;
  }

  /**
   * 获取年龄
   * @param starDate
   * @return
   */
  public static int getAgeByBirth(String starDate) {
    return dateDiffYear(starDate, getCurrentDate());
  }
}