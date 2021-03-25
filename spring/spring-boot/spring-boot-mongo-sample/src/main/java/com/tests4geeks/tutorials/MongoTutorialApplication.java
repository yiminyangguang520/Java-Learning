package com.tests4geeks.tutorials;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tests4geeks.tutorials.model.AbstractBaseEntity;
import com.tests4geeks.tutorials.model.RoleUser;
import com.tests4geeks.tutorials.model.TokenUser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author min
 */
@SpringBootApplication
@EnableMongoRepositories("com.tests4geeks.tutorials.repository")
public class MongoTutorialApplication {

  public static void main(String[] args) throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();

    RoleUser roleUser = new RoleUser();
    roleUser.setRoleName("role");
    roleUser.setPassword("rolePwd");
    roleUser.setUserName("roleUserName");


    TokenUser tokenUser = new TokenUser();
    tokenUser.setToken("token");
    tokenUser.setPassword("tokenPassword");
    tokenUser.setUserName("tokenUserName");

    String roleStr = objectMapper.writeValueAsString(roleUser);
    String tokenStr = objectMapper.writeValueAsString(tokenUser);

    System.out.println(roleStr);
    System.out.println(tokenStr);


    AbstractBaseEntity roleEntity = objectMapper.readValue(roleStr, AbstractBaseEntity.class);
    AbstractBaseEntity tokenEntity = objectMapper.readValue(tokenStr, AbstractBaseEntity.class);


    System.out.println(roleEntity);
    System.out.println(tokenEntity);
    SpringApplication.run(MongoTutorialApplication.class, args);
  }

}
