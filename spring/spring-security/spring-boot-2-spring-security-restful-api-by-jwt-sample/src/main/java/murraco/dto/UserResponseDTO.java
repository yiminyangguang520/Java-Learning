package murraco.dto;

import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import murraco.model.Role;

/**
 * @author min
 */
public class UserResponseDTO {

  @ApiModelProperty(position = 0)
  private Integer id;

  @ApiModelProperty(position = 1)
  private String username;

  @ApiModelProperty(position = 2)
  private String email;

  @ApiModelProperty(position = 3)
  List<Role> roles;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public List<Role> getRoles() {
    return roles;
  }

  public void setRoles(List<Role> roles) {
    this.roles = roles;
  }

  @Override
  public String toString() {
    return "UserResponseDTO{" +
        "id=" + id +
        ", username='" + username + '\'' +
        ", email='" + email + '\'' +
        ", roles=" + roles +
        '}';
  }
}
