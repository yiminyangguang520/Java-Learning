package guru.springframework.commands;


import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

/**
 * @author jt
 * @date 1/10/17
 */
@Getter
@Setter
public class ProductForm {

  private String id;
  private String description;
  private BigDecimal price;
  private String imageUrl;
}
