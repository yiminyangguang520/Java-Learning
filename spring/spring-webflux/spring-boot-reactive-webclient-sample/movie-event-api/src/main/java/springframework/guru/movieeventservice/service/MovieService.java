package springframework.guru.movieeventservice.service;

import reactor.core.publisher.Flux;
import springframework.guru.movieeventservice.domain.MovieEvent;


/**
 * @author litz-a
 */
public interface MovieService {

  /**
   * events
   * @return
   */
  Flux<MovieEvent> events();
}
