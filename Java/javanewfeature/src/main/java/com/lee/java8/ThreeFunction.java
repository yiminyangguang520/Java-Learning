package com.lee.java8;

/**
 * Created by lee on 2016/10/16.
 */
@FunctionalInterface
public interface ThreeFunction<T, U, K, R> {

  R apply(T t, U u, K k);
}
