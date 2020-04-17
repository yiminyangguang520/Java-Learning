package cn.merryyou.sso.service;

/**
 * @author min
 */
public interface UserService {

  /**
   * 账户认证
   * @param account
   * @param password
   * @return
   * @throws Exception
   */
  String accountAuthenticate(String account, String password) throws Exception;
}
