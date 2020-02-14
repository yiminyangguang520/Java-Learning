package com.algerfan.repository;

import com.algerfan.domain.User;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * @author bruce
 */
@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {

  /**
   * 根据年龄查找用户
   * @param start 年龄开始时间
   * @param end   年龄结束时间
   * @return 用户列表
   */
  Flux<User> findByAgeBetween(int start, int end);

  /**
   * old user
   * @return
   */
  @Query("{'age':{ '$gte': 20, '$lte' : 30}}")
  Flux<User> oldUser();
}
