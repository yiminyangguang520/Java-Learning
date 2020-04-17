package com.journaldev.bootifulmongodb.model;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author min
 */
@Document(collection = "person")
@CompoundIndexes({
    @CompoundIndex(name = "email_age", def = "{'name' : 1, 'age': -1}")
})
public class Person {

  @Id
  private String personId;

  @Indexed(direction = IndexDirection.ASCENDING)
  private String name;

  private long age;

  private List<String> favoriteBooks;

  private Date dateOfBirth;

  public Person() {
  }

  public Person(String name, List<String> childrenName, Date dateOfBirth) {
    this.name = name;
    this.favoriteBooks = childrenName;
    this.dateOfBirth = dateOfBirth;
    this.age = getDiffYears(dateOfBirth, new Date());
  }

  public String getPersonId() {
    return personId;
  }

  public void setPersonId(String personId) {
    this.personId = personId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public long getAge() {
    return age;
  }

  public void setAge(long age) {
    this.age = age;
  }

  public List<String> getFavoriteBooks() {
    return favoriteBooks;
  }

  public void setFavoriteBooks(List<String> favoriteBooks) {
    this.favoriteBooks = favoriteBooks;
  }

  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  private int getDiffYears(Date first, Date last) {
    Calendar a = getCalendar(first);
    Calendar b = getCalendar(last);
    int diff = b.get(Calendar.YEAR) - a.get(Calendar.YEAR);
    if (a.get(Calendar.MONTH) > b.get(Calendar.MONTH) ||
        (a.get(Calendar.MONTH) == b.get(Calendar.MONTH) && a.get(Calendar.DATE) > b.get(Calendar.DATE))) {
      diff--;
    }
    return diff;
  }

  private Calendar getCalendar(Date date) {
    Calendar cal = Calendar.getInstance(Locale.US);
    cal.setTime(date);
    return cal;
  }

  @Override
  public String toString() {
    return String.format("Person{personId='%s', name='%s', age=%d, dateOfBirth=%s}\n",
        personId, name, age, dateOfBirth);
  }
}