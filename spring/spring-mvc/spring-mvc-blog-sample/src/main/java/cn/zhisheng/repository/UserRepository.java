package cn.zhisheng.repository;

import cn.zhisheng.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author min
 * Created by 10412 on 2016/12/21.
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

  /**
   * 定义查询, @Param注解用于提取参数, @Modifying说明该方法是修改方法, Transactional说明该方法是事性操作
   * @param nickname
   * @param firstName
   * @param lastName
   * @param password
   * @param id
   */
  @Modifying
  @Transactional(rollbackFor = Exception.class)
  @Query("update UserEntity us set us.nickname=:qNickname, us.firstName=:qFirstName, us.lastName=:qLastName, us.password=:qPassword where us.id=:qId")
  void updateUser(@Param("qNickname") String nickname, @Param("qFirstName") String firstName,
      @Param("qLastName") String lastName, @Param("qPassword") String password, @Param("qId") Integer id);


}
