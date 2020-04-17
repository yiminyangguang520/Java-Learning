package util.redis;

import java.util.Map;
import java.util.Set;
import redis.clients.jedis.Jedis;

/**
 * RedisZSet通用工具类
 *
 * @author min
 */
public class RedisZSet {

  /**
   * 通过key向zset中添加value,score,其中score就是用来排序的 如果该value已经存在则根据score更新元素
   */
  public Long add(String key, Map<String, Double> scoreMembers) {
    Jedis jedis = RedisPoolUtil.getInstance();
    try {
      return jedis.zadd(key, scoreMembers);
    } finally {
      jedis.close();
    }
  }

  /**
   * 通过key向zset中添加value,score,其中score就是用来排序的 如果该value已经存在则根据score更新元素
   */
  public Long add(String key, double score, String member) {
    Jedis jedis = RedisPoolUtil.getInstance();
    try {
      return jedis.zadd(key, score, member);
    } finally {
      jedis.close();
    }
  }

  /**
   * 通过key删除在zset中指定的value
   *
   * @param members 可以使一个string 也可以是一个string数组
   */
  public Long rem(String key, String... members) {
    Jedis jedis = RedisPoolUtil.getInstance();
    try {
      return jedis.zrem(key, members);
    } finally {
      jedis.close();
    }
  }

  /**
   * 通过key增加该zset中value的score的值
   */
  public Double incrby(String key, double score, String member) {
    Jedis jedis = RedisPoolUtil.getInstance();
    try {
      return jedis.zincrby(key, score, member);
    } finally {
      jedis.close();
    }
  }

  /**
   * 通过key返回zset中value的排名 下标从小到大排序
   */
  public Long rank(String key, String member) {
    Jedis jedis = RedisPoolUtil.getInstance();
    try {
      return jedis.zrank(key, member);
    } finally {
      jedis.close();
    }
  }

  /**
   * 通过key返回zset中value的排名 下标从大到小排序
   */
  public Long revrank(String key, String member) {
    Jedis jedis = RedisPoolUtil.getInstance();
    try {
      return jedis.zrevrank(key, member);
    } finally {
      jedis.close();
    }
  }

  /**
   * 通过key将获取score从start到end中zset的value socre从大到小排序 当start为0 end为-1时返回全部
   */
  public Set<String> revRange(String key, long start, long end) {
    Jedis jedis = RedisPoolUtil.getInstance();
    try {
      return jedis.zrevrange(key, start, end);
    } finally {
      jedis.close();
    }
  }

  /**
   * 通过key返回指定score内zset中的value
   */
  public Set<String> rangeByScore(String key, String max, String min) {
    Jedis jedis = RedisPoolUtil.getInstance();
    try {
      return jedis.zrevrangeByScore(key, max, min);
    } finally {
      jedis.close();
    }
  }

  /**
   * 通过key返回指定score内zset中的value
   */
  public Set<String> rangeByScore(String key, double max, double min) {
    Jedis jedis = RedisPoolUtil.getInstance();
    try {
      return jedis.zrevrangeByScore(key, max, min);
    } finally {
      jedis.close();
    }
  }

  /**
   * 返回指定区间内zset中value的数量
   */
  public Long count(String key, String min, String max) {
    Jedis jedis = RedisPoolUtil.getInstance();
    try {
      return jedis.zcount(key, min, max);
    } finally {
      jedis.close();
    }
  }

  /**
   * 通过key返回zset中的value个数
   */
  public Long card(String key) {
    Jedis jedis = RedisPoolUtil.getInstance();
    try {
      return jedis.zcard(key);
    } finally {
      jedis.close();
    }
  }

  /**
   * 通过key获取zset中value的score值
   */
  public Double score(String key, String member) {
    Jedis jedis = RedisPoolUtil.getInstance();
    try {
      return jedis.zscore(key, member);
    } finally {
      jedis.close();
    }
  }

  /**
   * 通过key删除给定区间内的元素
   */
  public Long remrangeByRank(String key, long start, long end) {
    Jedis jedis = RedisPoolUtil.getInstance();
    try {
      return jedis.zremrangeByRank(key, start, end);
    } finally {
      jedis.close();
    }
  }

  /**
   * 通过key删除指定score内的元素
   */
  public Long remrangeByScore(String key, double start, double end) {
    Jedis jedis = RedisPoolUtil.getInstance();
    try {
      return jedis.zremrangeByScore(key, start, end);
    } finally {
      jedis.close();
    }
  }
}