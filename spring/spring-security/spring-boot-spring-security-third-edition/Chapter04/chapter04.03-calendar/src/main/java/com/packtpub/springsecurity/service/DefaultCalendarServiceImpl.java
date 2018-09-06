package com.packtpub.springsecurity.service;

import com.packtpub.springsecurity.dataaccess.CalendarUserDao;
import com.packtpub.springsecurity.dataaccess.EventDao;
import com.packtpub.springsecurity.domain.CalendarUser;
import com.packtpub.springsecurity.domain.Event;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

/**
 * A default implementation of {@link CalendarService} that delegates to {@link EventDao} and {@link CalendarUserDao}.
 *
 * @author Rob Winch
 * @author Mick Knutson
 */
@Repository
public class DefaultCalendarServiceImpl implements CalendarService {

  private final EventDao eventDao;
  private final CalendarUserDao userDao;
  private final JdbcOperations jdbcOperations;

  @Autowired
  public DefaultCalendarServiceImpl(final EventDao eventDao,
      final CalendarUserDao userDao,
      final JdbcOperations jdbcOperations) {
    if (eventDao == null) {
      throw new IllegalArgumentException("eventDao cannot be null");
    }
    if (userDao == null) {
      throw new IllegalArgumentException("userDao cannot be null");
    }
    if (jdbcOperations == null) {
      throw new IllegalArgumentException("jdbcOperations cannot be null");
    }
    this.eventDao = eventDao;
    this.userDao = userDao;
    this.jdbcOperations = jdbcOperations;
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
    int userId = userDao.createUser(user);
    jdbcOperations.update("insert into calendar_user_authorities(calendar_user,authority) values (?,?)", userId,
        "ROLE_USER");
    return userId;
  }
}