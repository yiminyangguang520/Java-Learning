package springframework.guru.webclientdemo.service;

import reactor.core.publisher.Mono;
import springframework.guru.webclientdemo.domain.Movie;

/**
 * @author min
 */
public interface MovieClientService {

  /**
   * searchMovieByTitle
   * @param apiKey
   * @param title
   * @return
   */
  Mono<Movie> searchMovieByTitle(String apiKey, String title);

  /**
   * searchMovieById
   * @param apiKey
   * @param imdbId
   * @return
   */
  Mono<Movie> searchMovieById(String apiKey, String imdbId);


}