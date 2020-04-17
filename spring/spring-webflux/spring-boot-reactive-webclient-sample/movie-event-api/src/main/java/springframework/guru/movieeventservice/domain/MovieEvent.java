package springframework.guru.movieeventservice.domain;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author min
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieEvent {

  private String eventMessage;
  private Date date;
}

