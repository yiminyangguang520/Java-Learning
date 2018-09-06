package com.packtpub.springsecurity.domain;

import java.util.Calendar;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * An {@link Event} is an item on a calendar that contains an owner (the person who created it), an attendee (someone who was invited to the event), when the event will
 * occur, a summary, and a description. For simplicity, all fields are required.
 *
 * @author Rob Winch
 * @author Mick Knutson
 */
public class Event {

  private Integer id;

  @NotEmpty(message = "Summary is required")
  private String summary;

  @NotEmpty(message = "Description is required")
  private String description;

  @NotNull(message = "When is required")
  private Calendar when;

  @NotNull(message = "owner is required")
  private CalendarUser owner;

  private CalendarUser attendee;

  /**
   * The identifier for the {@link Event}. Must be null when creating a new {@link Event}, otherwise non-null.
   */
  public Integer getId() {
    return id;
  }

  /**
   * The summary of the event.
   */
  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * The detailed description of the event.
   */
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * When this event is happening.
   */
  public Calendar getWhen() {
    return when;
  }

  public void setWhen(Calendar when) {
    this.when = when;
  }

  /**
   * The owner (who created the Event)
   */
  public CalendarUser getOwner() {
    return owner;
  }

  public void setOwner(CalendarUser owner) {
    this.owner = owner;
  }

  /**
   * The user that was invited to the event.
   */
  public CalendarUser getAttendee() {
    return attendee;
  }

  public void setAttendee(CalendarUser attendee) {
    this.attendee = attendee;
  }

  // --- override Object ---

  @Override
  public int hashCode() {
    //return HashCodeBuilder.reflectionHashCode(this);
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    //return EqualsBuilder.reflectionEquals(this, obj);
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Event other = (Event) obj;
    if (id == null) {
      if (other.id != null) {
        return false;
      }
    } else if (!id.equals(other.id)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this);
  }
}