package org.lee.mybatis.controller;

import java.io.Serializable;
import java.util.List;

public interface BasicController<E, I extends Serializable> {

  int deleteById(I id);

  int save(E entity);

  List<E> findAll();

  E findById(I id);

  int update(E record);
}
