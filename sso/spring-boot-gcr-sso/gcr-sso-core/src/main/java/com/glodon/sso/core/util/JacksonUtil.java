package com.glodon.sso.core.util;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Jackson util
 *
 * 1、obj need private and set/get
 * 2、do not support inner class
 *
 * @author litz-a
 */
public class JacksonUtil {

  private static Logger logger = LoggerFactory.getLogger(JacksonUtil.class);

  private final static ObjectMapper objectMapper = new ObjectMapper();

  public static ObjectMapper getInstance() {
    return objectMapper;
  }

  /**
   * bean、array、List、Map --> json
   *
   * @return json string
   */
  public static String writeValueAsString(Object obj) {
    try {
      return getInstance().writeValueAsString(obj);
    } catch (JsonGenerationException e) {
      logger.error(e.getMessage(), e);
    } catch (JsonMappingException e) {
      logger.error(e.getMessage(), e);
    } catch (IOException e) {
      logger.error(e.getMessage(), e);
    }
    return null;
  }

  /**
   * string --> bean、Map、List(array)
   *
   * @return obj
   */
  public static <T> T readValue(String jsonStr, Class<T> clazz) {
    try {
      return getInstance().readValue(jsonStr, clazz);
    } catch (JsonParseException e) {
      logger.error(e.getMessage(), e);
    } catch (JsonMappingException e) {
      logger.error(e.getMessage(), e);
    } catch (IOException e) {
      logger.error(e.getMessage(), e);
    }
    return null;
  }
}
