package com.marcosbarbero.wd.multiplemongo.repository.secondary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Marcos Barbero
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "second_mongo")
public class SecondaryModel {

  @Id
  private String id;

  private String value;

  @Override
  public String toString() {
    return "SecondaryModel{" + "id='" + id + '\'' + ", value='" + value + '\''
        + '}';
  }
}
