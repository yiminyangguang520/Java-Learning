package util.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

/**
 * RedisConfig 初始化连接池
 *
 * @author min
 */
public abstract class RedisPoolUtil {

  private static JedisPool pool = null;

  static {
    pool = new JedisPool(new JedisPoolConfig(), "192.168.1.100", Protocol.DEFAULT_PORT, 60000, "123456");
  }

  /**
   * 从非切片池中获取Jedis实例。
   */
  public static Jedis getInstance() {
    return pool.getResource();
  }
}