package springframework.guru.webclientdemo.service;

import reactor.core.publisher.Flux;
import springframework.guru.webclientdemo.domain.MovieEvent;

/**
 * @author litz-a
 */
public interface MovieClientEventService {

  /**
   * getMovieEvents
   * @return
   */
  Flux<MovieEvent> getMovieEvents();
}
