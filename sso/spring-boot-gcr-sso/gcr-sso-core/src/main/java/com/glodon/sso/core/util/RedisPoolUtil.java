package com.glodon.sso.core.util;

import com.google.gson.Gson;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

/**
 * RedisConfig 初始化连接池
 *
 * @author litz-a
 */
public abstract class RedisPoolUtil {

  private static final int DEFAULT_EXPIRE_TIME = 7200;

  private static JedisPool pool;

  static {
    pool = new JedisPool(new JedisPoolConfig(), "192.168.144.45", Protocol.DEFAULT_PORT, 60000);
  }

  /**
   * 从非切片池中获取Jedis实例。
   */
  public static Jedis getInstance() {
    return pool.getResource();
  }

  public static void set(String key, Object object) {
    getInstance().set(key, new Gson().toJson(object));
  }

  public static String get(String key) {
    return getInstance().get(key);
  }

  public static long del(String key) {
    return getInstance().del(key);
  }

  public static String setObjectValue(String key, Object obj) {
    return setObjectValue(key, obj, DEFAULT_EXPIRE_TIME);
  }

  public static String setObjectValue(String key, Object obj, int seconds) {
    String result = null;
    Jedis client = getInstance();
    try {
      result = client.setex(key.getBytes(), seconds, serialize(obj));
    } catch (Exception e) {
    } finally {
      client.close();
    }
    return result;
  }

  public static Object getObjectValue(String key) {
    Object obj = null;
    Jedis client = getInstance();
    try {
      byte[] bytes = client.get(key.getBytes());
      if (bytes != null && bytes.length > 0) {
        obj = unserialize(bytes);
      }
    } catch (Exception e) {
    } finally {
      client.close();
    }
    return obj;
  }

  private static byte[] serialize(Object object) {
    ObjectOutputStream oos = null;
    ByteArrayOutputStream baos = null;
    try {
      // 序列化
      baos = new ByteArrayOutputStream();
      oos = new ObjectOutputStream(baos);
      oos.writeObject(object);
      byte[] bytes = baos.toByteArray();
      return bytes;
    } catch (Exception e) {
    } finally {
      try {
        oos.close();
        baos.close();
      } catch (IOException e) {
      }
    }
    return null;
  }

  /**
   * 将byte[] -->Object
   */
  private static Object unserialize(byte[] bytes) {
    ByteArrayInputStream bais = null;
    try {
      // 反序列化
      bais = new ByteArrayInputStream(bytes);
      ObjectInputStream ois = new ObjectInputStream(bais);
      return ois.readObject();
    } catch (Exception e) {
    } finally {
      try {
        bais.close();
      } catch (IOException e) {
      }
    }
    return null;
  }
}