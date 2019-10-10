package com.example.webfluxdemo.exception;

/**
 *
 * @author rajeevkumarsingh
 * @date 22/10/17
 */
public class TweetNotFoundException extends RuntimeException {

  public TweetNotFoundException(String tweetId) {
    super("Tweet not found with id " + tweetId);
  }
}
