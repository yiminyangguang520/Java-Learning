package com.packtpub.springsecurity.service;

import com.packtpub.springsecurity.dataaccess.CalendarUserDao;
import com.packtpub.springsecurity.dataaccess.EventDao;
import com.packtpub.springsecurity.domain.CalendarUser;
import com.packtpub.springsecurity.domain.Event;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Repository;

/**
 * A default implementation of {@link CalendarService} that delegates to {@link EventDao} and {@link CalendarUserDao}.
 *
 * @author Rob Winch
 */
@Repository
public class DefaultCalendarServiceImpl implements CalendarService {

  private final EventDao eventDao;
  private final CalendarUserDao userDao;
  private final UserDetailsManager userDetailsManager;

  @Autowired
  public DefaultCalendarServiceImpl(EventDao eventDao, CalendarUserDao userDao, @Qualifier("userDetailsService") UserDetailsManager userDetailsManager) {
    if (eventDao == null) {
      throw new IllegalArgumentException("eventDao cannot be null");
    }
    if (userDao == null) {
      throw new IllegalArgumentException("userDao cannot be null");
    }
    if (userDetailsManager == null) {
      throw new IllegalArgumentException("userDetailsManager cannot be null");
    }
    this.eventDao = eventDao;
    this.userDao = userDao;
    this.userDetailsManager = userDetailsManager;
  }

  @Override
  public Event getEvent(int eventId) {
    return eventDao.getEvent(eventId);
  }

  @Override
  public int createEvent(Event event) {
    return eventDao.createEvent(event);
  }

  @Override
  public List<Event> findForUser(int userId) {
    return eventDao.findForUser(userId);
  }

  @Override
  public List<Event> getEvents() {
    return eventDao.getEvents();
  }

  @Override
  public CalendarUser getUser(int id) {
    return userDao.getUser(id);
  }

  @Override
  public CalendarUser findUserByEmail(String email) {
    return userDao.findUserByEmail(email);
  }

  @Override
  public List<CalendarUser> findUsersByEmail(String partialEmail) {
    return userDao.findUsersByEmail(partialEmail);
  }

  @Override
  public int createUser(CalendarUser user) {
    List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_USER");
    UserDetails userDetails = new User(user.getEmail(), user.getPassword(), authorities);
    userDetailsManager.createUser(userDetails);
    return userDao.createUser(user);
  }
}