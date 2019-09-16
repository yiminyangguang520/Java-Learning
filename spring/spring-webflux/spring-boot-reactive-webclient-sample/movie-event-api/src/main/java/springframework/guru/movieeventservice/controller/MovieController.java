package springframework.guru.movieeventservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import springframework.guru.movieeventservice.domain.MovieEvent;
import springframework.guru.movieeventservice.service.MovieService;

/**
 * @author litz-a
 */
@RestController
@RequestMapping(path = "/api/v1/movies")
public class MovieController {

  private MovieService movieService;

  @Autowired
  public MovieController(final MovieService movieService) {
    this.movieService = movieService;
  }

  @GetMapping(value = "events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  Flux<MovieEvent> streamMovieEvents() {
    return movieService.events();
  }
}
