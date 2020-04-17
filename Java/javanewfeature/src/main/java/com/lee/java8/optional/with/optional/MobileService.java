package com.lee.java8.optional.with.optional;

import java.util.Optional;

/**
 * @author min
 */
public class MobileService {

  public Integer getMobileScreenWidth(Optional<Mobile> mobile) {
    return mobile.flatMap(Mobile::getDisplayFeatures)
        .flatMap(DisplayFeatures::getResolution)
        .map(ScreenResolution::getWidth)
        .orElse(0);
  }

}
