package com.michaelcgood.repository;

import com.michaelcgood.model.Movie;
import java.util.Map;

/**
 * @author min
 */
public interface RedisRepository {

  /**
   * Return all movies
   * @return
   */
  Map<Object, Object> findAllMovies();

  /**
   * Add key-value pair to Redis.
   * @param movie
   */
  void add(Movie movie);

  /**
   * Delete a key-value pair in Redis.
   * @param id
   */
  void delete(String id);

  /**
   * find a movie
   * @param id
   * @return
   */
  Movie findMovie(String id);

}
