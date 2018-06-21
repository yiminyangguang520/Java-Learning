package com.biostime.demo.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * Function: jackson json解析帮助类
 * <P> 版权所有 ©2013 Biostime Inc.. All Rights Reserved
 * <p> 未经本公司许可，不得以任何方式复制或使用本程序任何部分 <p>
 * User: 12360
 * Date: 2016/7/26
 * Time: 17:03
 */
public class JsonUtils {
    private static final Logger logger = Logger.getLogger(JsonUtils.class.getName());
    private static ObjectMapper mapper = new ObjectMapper();
    public static String toString(Object ojb) {
        try {
            return mapper.writeValueAsString(ojb);
        } catch (JsonProcessingException e) {
            logger.info("JSON object transformation exception ：" + e.getMessage());
        }
        return "";
    }

    public static <T> T toJSON(String jsonString, Class<T> classes) {
        try {
            return mapper.readValue(jsonString, classes);
        } catch (JsonProcessingException e) {
            logger.info("JSON object transformation exception ：" + e.getMessage());
        } catch (IOException e) {
            logger.info("JSON object transformation exception ："+e.getMessage());
        }
        return null;
    }
}
