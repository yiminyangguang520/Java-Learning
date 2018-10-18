package com.luoshupeng.multidatasource.secondary.repository;

import com.luoshupeng.multidatasource.secondary.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author litz-a
 * Created by luoshupeng on 2018-03-20 10:24
 */
public interface MessageRepository extends JpaRepository<Message, Integer> {

}
