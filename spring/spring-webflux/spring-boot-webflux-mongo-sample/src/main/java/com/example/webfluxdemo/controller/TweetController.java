package com.example.webfluxdemo.controller;

import com.example.webfluxdemo.exception.TweetNotFoundException;
import com.example.webfluxdemo.model.Tweet;
import com.example.webfluxdemo.payload.ErrorResponse;
import com.example.webfluxdemo.repository.TweetRepository;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author rajeevkumarsingh
 * @date 08/09/17
 */
@RestController
public class TweetController {

  @Autowired
  private TweetRepository tweetRepository;

  @GetMapping("/tweets")
  public Flux<Tweet> getAllTweets() {
    return tweetRepository.findAll();
  }

  @PostMapping("/tweets")
  public Mono<Tweet> createTweets(@Valid @RequestBody Tweet tweet) {
    return tweetRepository.save(tweet);
  }

  @GetMapping("/tweets/{id}")
  public Mono<ResponseEntity<Tweet>> getTweetById(@PathVariable(value = "id") String tweetId) {
    return tweetRepository.findById(tweetId)
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @PutMapping("/tweets/{id}")
  public Mono<ResponseEntity<Tweet>> updateTweet(@PathVariable(value = "id") String tweetId,
      @Valid @RequestBody Tweet tweet) {
    return tweetRepository.findById(tweetId)
        .flatMap(existingTweet -> {
          existingTweet.setText(tweet.getText());
          return tweetRepository.save(existingTweet);
        })
        .map(updateTweet -> new ResponseEntity<>(updateTweet, HttpStatus.OK))
        .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @DeleteMapping("/tweets/{id}")
  public Mono<ResponseEntity<Void>> deleteTweet(@PathVariable(value = "id") String tweetId) {

    return tweetRepository.findById(tweetId)
        .flatMap(existingTweet ->
            tweetRepository.delete(existingTweet)
                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
        )
        .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  /**
   * Tweets are Sent to the client as Server Sent Events
   * @return
   */
  @GetMapping(value = "/stream/tweets", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<Tweet> streamAllTweets() {
    return tweetRepository.findAll();
  }

  /**
   * Exception Handling Examples (These can be put into a @ControllerAdvice to handle exceptions globally)
   * @param ex
   * @return
   */
  @ExceptionHandler(DuplicateKeyException.class)
  public ResponseEntity handleDuplicateKeyException(DuplicateKeyException ex) {
    return ResponseEntity
        .status(HttpStatus.CONFLICT)
        .body(new ErrorResponse("A Tweet with the same text already exists"));
  }

  @ExceptionHandler(TweetNotFoundException.class)
  public ResponseEntity handleTweetNotFoundException(TweetNotFoundException ex) {
    return ResponseEntity.notFound().build();
  }

}
