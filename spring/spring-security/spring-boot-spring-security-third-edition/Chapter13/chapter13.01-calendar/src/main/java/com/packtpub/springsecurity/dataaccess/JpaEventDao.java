package com.packtpub.springsecurity.dataaccess;

import com.packtpub.springsecurity.domain.CalendarUser;
import com.packtpub.springsecurity.domain.Event;
import com.packtpub.springsecurity.repository.EventRepository;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * A jdbc implementation of {@link EventDao}.
 *
 * @author Rob Winch
 * @author Mick Knutson
 */
@Repository
public class JpaEventDao implements EventDao {

  private EventRepository eventRepository;

  @Autowired
  public JpaEventDao(EventRepository eventRepository) {
    if (eventRepository == null) {
      throw new IllegalArgumentException("eventRepository cannot be null");
    }
    this.eventRepository = eventRepository;
  }

  @Override
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public Event getEvent(int eventId) {
    return eventRepository.findById(eventId).get();
  }

  @Override
  public int createEvent(final Event event) {
    if (event == null) {
      throw new IllegalArgumentException("event cannot be null");
    }
    if (event.getId() != null) {
      throw new IllegalArgumentException("event.getId() must be null when creating a new Message");
    }
    final CalendarUser owner = event.getOwner();
    if (owner == null) {
      throw new IllegalArgumentException("event.getOwner() cannot be null");
    }
    final CalendarUser attendee = event.getAttendee();
    if (attendee == null) {
      throw new IllegalArgumentException("attendee.getOwner() cannot be null");
    }
    final Calendar when = event.getWhen();
    if (when == null) {
      throw new IllegalArgumentException("event.getWhen() cannot be null");
    }
    Event newEvent = eventRepository.save(event);
    return newEvent.getId();
  }

  @Override
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public List<Event> findForUser(final int userId) {
    Event example = new Event();
    CalendarUser cu = new CalendarUser();
    cu.setId(userId);
    example.setOwner(cu);

    return eventRepository.findAll(Example.of(example));
  }

  @Override
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public List<Event> findForUser(final String email) {
    Event example = new Event();
    CalendarUser cu = new CalendarUser();
    cu.setEmail(email);
    example.setOwner(cu);

    return eventRepository.findAll(Example.of(example));
  }

  @Override
  @Transactional(readOnly = true, rollbackFor = Exception.class)
  public List<Event> getEvents() {
    return eventRepository.findAll();
  }

}
