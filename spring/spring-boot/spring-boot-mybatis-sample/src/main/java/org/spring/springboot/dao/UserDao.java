package org.spring.springboot.dao;


import org.spring.springboot.domain.User;

import java.util.List;

/**
 * Created by min on 2017/5/24.
 */
public interface UserDao {
    List<User> getUOBN(String userNumber);
}
