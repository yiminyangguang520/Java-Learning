package cn.merryyou.sso.service.impl;

import cn.merryyou.sso.service.UserService;
import okhttp3.OkHttpClient;
import org.springframework.stereotype.Service;

/**
 * @author litz-a
 */
@Service
public class UserServiceImpl implements UserService {

  private OkHttpClient okClient = new OkHttpClient();

  /**
   * 账户认证
   */
  @Override
  public String accountAuthenticate(String account, String password) throws Exception {

    return "Y";
  }
}
