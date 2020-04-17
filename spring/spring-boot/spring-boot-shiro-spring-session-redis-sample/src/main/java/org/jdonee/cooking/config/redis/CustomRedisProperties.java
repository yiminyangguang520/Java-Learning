package org.jdonee.cooking.config.redis;

import java.time.Duration;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author min
 */
@Data
@Configuration
@NoArgsConstructor
@ConfigurationProperties(prefix = "spring.redis")
public class CustomRedisProperties {

  /**
   * Database index used by the connection factory.
   */
  private int database = 0;

  private int secondaryDatabase = 1;

  /**
   * Redis url, which will overrule host, port and password if set.
   */
  private String url;

  /**
   * Redis server host.
   */
  private String host = "localhost";

  /**
   * Login password of the redis server.
   */
  private String password;

  /**
   * Redis server port.
   */
  private int port = 6379;

  /**
   * Enable SSL.
   */
  private boolean ssl;

  /**
   * Connection timeout in milliseconds.
   */
  private int timeout;

  private int sessionExpire;

  private final Jedis jedis = new Jedis();

  private final Lettuce lettuce = new Lettuce();

  /**
   * Jedis client properties.
   */
  public static class Jedis {

    /**
     * Jedis pool configuration.
     */
    private Pool pool;

    public Pool getPool() {
      return this.pool;
    }

    public void setPool(Pool pool) {
      this.pool = pool;
    }

  }

  /**
   * Lettuce client properties.
   */
  public static class Lettuce {

    /**
     * Shutdown timeout.
     */
    private Duration shutdownTimeout = Duration.ofMillis(100);

    /**
     * Lettuce pool configuration.
     */
    private Pool pool;

    public Duration getShutdownTimeout() {
      return this.shutdownTimeout;
    }

    public void setShutdownTimeout(Duration shutdownTimeout) {
      this.shutdownTimeout = shutdownTimeout;
    }

    public Pool getPool() {
      return this.pool;
    }

    public void setPool(Pool pool) {
      this.pool = pool;
    }

  }

  /**
   * Pool properties.
   */
  @Getter
  @Setter
  public static class Pool {

    /**
     * Max number of "idle" connections in the pool. Use a negative value to indicate an unlimited number of idle connections.
     */
    private int maxIdle = 8;

    /**
     * Target for the minimum number of idle connections to maintain in the pool. This setting only has an effect if it is positive.
     */
    private int minIdle = 0;

    /**
     * Max number of connections that can be allocated by the pool at a given time. Use a negative value for no limit.
     */
    private int maxActive = 8;

    /**
     * Maximum amount of time (in milliseconds) a connection allocation should block before throwing an exception when the pool is exhausted. Use a negative value to
     * block indefinitely.
     */
    private int maxWait = -1;
  }

}
