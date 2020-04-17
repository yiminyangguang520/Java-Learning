package murraco.dto;

import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import murraco.model.Role;

/**
 * @author min
 */
public class UserDataDTO {

  @ApiModelProperty(position = 0)
  private String username;

  @ApiModelProperty(position = 1)
  private String email;

  @ApiModelProperty(position = 2)
  List<Role> roles;

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
    return "UserDataDTO{" +
        "username='" + username + '\'' +
        ", email='" + email + '\'' +
        ", roles=" + roles +
        '}';
  }
}
