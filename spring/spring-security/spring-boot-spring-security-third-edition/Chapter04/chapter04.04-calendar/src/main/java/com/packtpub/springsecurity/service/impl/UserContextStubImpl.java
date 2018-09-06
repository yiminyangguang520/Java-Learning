package com.packtpub.springsecurity.service.impl;

import com.packtpub.springsecurity.dataaccess.CalendarUserDao;
import com.packtpub.springsecurity.domain.CalendarUser;
import com.packtpub.springsecurity.service.UserContext;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * NOTE: This is no longer used. See {@link SpringSecurityUserContextImpl}.
 *
 * Returns the same user for every call to {@link #getCurrentUser()}. This is used prior to adding security, so that the rest of the application can be used.
 *
 * @author Rob Winch
 * @see SpringSecurityUserContextImpl
 */
//@Component
public class UserContextStubImpl implements UserContext {

  private final CalendarUserDao userService;
  /**
   * The {@link CalendarUser#getId()} for the user that is representing the currently logged in user. This can be modified using {@link #setCurrentUser(CalendarUser)}
   */
  private int currentUserId = 0;

  @Autowired
  public UserContextStubImpl(CalendarUserDao userService) {
    if (userService == null) {
      throw new IllegalArgumentException("userService cannot be null");
    }
    this.userService = userService;
  }

  @Override
  public CalendarUser getCurrentUser() {
    return userService.getUser(currentUserId);
  }

  @Override
  public void setCurrentUser(CalendarUser user) {
    if (user == null) {
      throw new IllegalArgumentException("user cannot be null");
    }
    Integer currentId = user.getId();
    if (currentId == null) {
      throw new IllegalArgumentException("user.getId() cannot be null");
    }
    this.currentUserId = currentId;
  }
}