package com.lee.java8;

/**
 *
 * @author lee
 * @date 2016/10/16
 */
@FunctionalInterface
public interface ThreeFunction<T, U, K, R> {

  /**
   * apply
   * @param t
   * @param u
   * @param k
   * @return
   */
  R apply(T t, U u, K k);
}
