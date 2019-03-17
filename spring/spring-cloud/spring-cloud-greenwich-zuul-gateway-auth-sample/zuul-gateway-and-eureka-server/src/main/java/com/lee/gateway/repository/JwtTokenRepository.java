package com.lee.gateway.repository;

import com.lee.gateway.bean.auth.JwtToken;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author litz-a
 */
@Repository
public interface JwtTokenRepository extends MongoRepository<JwtToken, String> {

}
