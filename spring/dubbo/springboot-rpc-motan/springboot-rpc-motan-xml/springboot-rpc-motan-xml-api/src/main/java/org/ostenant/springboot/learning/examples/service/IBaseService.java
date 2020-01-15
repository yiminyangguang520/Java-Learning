package org.ostenant.springboot.learning.examples.service;

import java.io.Serializable;
import java.util.List;

public interface IBaseService<E, I extends Serializable> {

    int deleteById(I id);

    int save(E record);

    List<E> findAll();

    E findById(I id);

    int update(E record);
}
