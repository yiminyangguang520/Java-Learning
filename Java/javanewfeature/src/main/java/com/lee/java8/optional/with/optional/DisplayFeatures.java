package com.lee.java8.optional.with.optional;

import java.util.Optional;

/**
 * @author litz-a
 */
public class DisplayFeatures {

  // In inches
  private String size;
  private Optional<ScreenResolution> resolution;

  public DisplayFeatures(String size, Optional<ScreenResolution> resolution) {
    this.size = size;
    this.resolution = resolution;
  }

  public String getSize() {
    return size;
  }

  public Optional<ScreenResolution> getResolution() {
    return resolution;
  }

}
