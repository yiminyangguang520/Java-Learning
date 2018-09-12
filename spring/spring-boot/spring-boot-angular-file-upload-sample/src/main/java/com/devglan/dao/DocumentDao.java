package com.devglan.dao;

import com.devglan.model.Document;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author litz-a
 */
@Repository
public interface DocumentDao extends CrudRepository<Document, Long> {

}
