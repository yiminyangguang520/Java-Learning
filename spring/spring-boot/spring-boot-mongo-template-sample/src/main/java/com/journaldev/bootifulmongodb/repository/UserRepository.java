package com.journaldev.bootifulmongodb.repository;

import com.journaldev.bootifulmongodb.model.User;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author min
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
