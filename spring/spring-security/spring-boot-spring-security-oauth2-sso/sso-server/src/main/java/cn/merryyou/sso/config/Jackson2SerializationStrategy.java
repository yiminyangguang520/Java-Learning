package cn.merryyou.sso.config;

import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.security.oauth2.provider.token.store.redis.StandardStringSerializationStrategy;
import org.springframework.stereotype.Component;

/**
 * @author litz-a
 */
@Component
public class Jackson2SerializationStrategy extends StandardStringSerializationStrategy {

  private Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(Object.class);

  @Override
  protected <T> T deserializeInternal(byte[] bytes, Class<T> clazz) {
    return (T) serializer.deserialize(bytes);
  }

  @Override
  protected byte[] serializeInternal(Object object) {
    return serializer.serialize(object);
  }

  public Jackson2JsonRedisSerializer getSerializer() {
    return serializer;
  }

  public void setSerializer(Jackson2JsonRedisSerializer serializer) {
    this.serializer = serializer;
  }
}