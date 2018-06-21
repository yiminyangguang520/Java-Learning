package com.biostime.demo.util;

/**
 * Function:
 * <P> 版权所有 ©2013 Biostime Inc.. All Rights Reserved
 * <p> 未经本公司许可，不得以任何方式复制或使用本程序任何部分 <p>
 * User: 12360
 * Date: 2016/11/11
 * Time: 15:58
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Function:属性文件工具类
 * <P> 版权所有 ©2013 Biostime Inc.. All Rights Reserved
 * <p> 未经本公司许可，不得以任何方式复制或使用本程序任何部分 <p>
 * User: 12360
 * Date: 2015/11/10
 * Time: 14:32
 */
public class PropUtils {
    private static final String propFileName = "/config.properties";
    private static final Logger logger = Logger.getLogger(PropUtils.class.getName());
    private static Properties prop = null;

    static {
        prop = new Properties();
        try {
            prop.load(PropUtils.class.getResourceAsStream(propFileName));
        } catch (FileNotFoundException e) {
            logger.info("the property file is not found!");
        } catch (IOException e) {
            logger.info("reading property file error,please check!");
        }
    }

    private static String replaceChars(String value, String[] messages) {
        MessageFormat format = new MessageFormat(value);
        return format.format(messages);
    }

    public static String getPropValue(String key, String[] messages) {
        String value = prop.getProperty(key);
        return replaceChars(value, messages);
    }

    public static String getProp(String key) {

        String value = prop.getProperty(key);
        if (value == null) {
            logger.info("the properties is not found.");
            return "系统异常";
        } else {
            return value;
        }
    }

    public static String getProp(String key, String defaultValue) {

        String value = prop.getProperty(key);
        if (value == null) {
            return defaultValue;
        } else {
            return value;
        }
    }

    public static String[] getProps(String key) {
        String value = prop.getProperty(key);
        return value.split(",");
    }

    public static String getProp(int key) {
        return getProp(String.valueOf(key));
    }

    public static String getProp(int key, String[] msg) {
        return getPropValue(String.valueOf(key), msg);
    }

}

