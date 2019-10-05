package com.lee.async.service;

import java.util.UUID;
import org.springframework.stereotype.Service;

/**
 * @author bruce
 */
@Service
public class WebAsyncService {

  public String generateUUID() {
    return UUID.randomUUID().toString();
  }

}
