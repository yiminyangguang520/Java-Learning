package com.itstyle.jwt.service.impl;

import com.itstyle.jwt.constant.Constant;
import com.itstyle.jwt.model.User;
import com.itstyle.jwt.repository.UserRepository;
import com.itstyle.jwt.service.UserService;
import java.io.StringReader;
import java.text.MessageFormat;
import java.util.Date;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import lombok.Cleanup;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * @author min
 */
@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  private OkHttpClient okClient = new OkHttpClient();

  /**
   * 账户认证
   */
  @Override
  public String accountAuthenticate(String account, String password) throws Exception {
    return "Y";
  }

  /**
   * save user
   */
  @Override
  public User saveUser(String username) {
    User user = new User();
    user.setUsername(username);
    user.setEmail(username + Constant.EMAIL_DOMAIN);
    user.setCreateTime(new Date());
    return userRepository.save(user);
  }

  /**
   * findByUserName
   */
  @Override
  public User findByUserName(String username) {
    return userRepository.findByUsername(username);
  }

  /**
   * getOrCreateUser
   */
  @Override
  public User getOrCreateUser(String username) {
    User user = findByUserName(username);

    if (user != null) {
      return user;
    }

    return saveUser(username);
  }
}
