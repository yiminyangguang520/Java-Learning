package springframework.guru.webclientdemo.service;

import reactor.core.publisher.Flux;
import springframework.guru.webclientdemo.domain.MovieEvent;

/**
 * @author min
 */
public interface MovieClientEventService {

  /**
   * getMovieEvents
   * @return
   */
  Flux<MovieEvent> getMovieEvents();
}
