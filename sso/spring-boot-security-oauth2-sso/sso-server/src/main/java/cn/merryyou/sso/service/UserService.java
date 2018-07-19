package cn.merryyou.sso.service;

/**
 * @author litz-a
 */
public interface UserService {

  /**
   * 域账户认证
   * @param account
   * @param password
   * @return
   * @throws Exception
   */
  String domainAccountAuthenticate(String account, String password) throws Exception;
}
