package org.lee.mybatis.service;

import java.io.Serializable;
import java.util.List;

/**
 * @author bruce
 */
public interface BasicDaoSupport<E, I extends Serializable> {

  int deleteById(I id);

  int save(E record);

  List<E> findAll();

  E findById(I id);

  int update(E record);
}
