package guru.springframework.commands;


import java.math.BigDecimal;

/**
 *
 * @author jt
 * @date 1/10/17
 */
public class ProductForm {

  private String id;
  private String description;
  private BigDecimal price;
  private String imageUrl;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }
}
