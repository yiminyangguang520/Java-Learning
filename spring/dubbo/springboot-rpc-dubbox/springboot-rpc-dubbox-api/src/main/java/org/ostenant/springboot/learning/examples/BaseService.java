package org.ostenant.springboot.learning.examples;

import java.io.Serializable;
import java.util.List;


public interface BaseService<E, I extends Serializable> {

    int deleteById(I id);

    int save(E record);

    List<E> findAll();

    E findById(I id);

    int update(E record);
}
