package cn.com.hellowood.rocketmq.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author HelloWood
 * @create 2017-09-01 21:46
 * @email hellowoodes@outlook.com
 **/

public class RocketMQServiceUtil {

  private static Logger logger = LoggerFactory.getLogger(RocketMQServiceUtil.class);

  /**
   * Transfer date string to timestamp
   */
  public static Long getTimestampByDateString(String dateString) throws ParseException {
    Long time = null;
    if (StringUtils.isNotEmpty(dateString)) {
      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      Date date = format.parse(dateString);
      time = date.getTime();
    }
    return time;
  }

  /**
   * Format date to String
   */
  public static String formatDateToString(Date date) {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    return format.format(date);
  }
}
