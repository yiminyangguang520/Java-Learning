package com.websystique.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * @author min
 */
@Service("fileService")
public class FileServiceImpl implements FileService {

  @Value("${sourceLocation:c:/temp/input}")
  private String source;

  @Value("${destinationLocation:c:/temp/output}")
  private String destination;

  @Autowired
  private Environment environment;

  public void readValues() {

    System.out.println("Getting property via Spring Environment :"
        + environment.getProperty("jdbc.driverClassName"));

    System.out.println("Source Location : " + source);
    System.out.println("Destination Location : " + destination);

  }

}
