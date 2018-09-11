package guru.springframework.domain;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

/**
 *
 * @author jt
 * @date 1/10/17
 */
@Getter
@Setter
@RedisHash("products")
public class Product {

  @Id
  private String id;
  private String description;
  private BigDecimal price;
  private String imageUrl;
}
