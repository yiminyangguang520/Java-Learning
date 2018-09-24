package asia.embla.dto;

/**
 *
 * @author tharsan
 * @date 4/24/18
 */
public class CountryCodeDTO {

  private Integer id;
  private String name;
  private String code;
  private String isoCode;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getIsoCode() {
    return isoCode;
  }

  public void setIsoCode(String isoCode) {
    this.isoCode = isoCode;
  }

  @Override
  public String toString() {
    return "CountryCodeDTO{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", code='" + code + '\'' +
        ", isoCode='" + isoCode + '\'' +
        '}';
  }
}
