package cn.zhisheng.repository;

import cn.zhisheng.model.BlogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author min
 * Created by 10412 on 2016/12/22.
 */
@Repository
public interface BlogRepository extends JpaRepository<BlogEntity, Integer> {

  /**
   * 修改博文操作
   * @param title
   * @param userId
   * @param content
   * @param pubDate
   * @param id
   */
  @Modifying
  @Transactional(rollbackFor = Exception.class)
  @Query("update BlogEntity blog set blog.title=:qTitle, blog.userByUserId.id=:qUserId," +
      " blog.content=:qContent, blog.pubDate=:qPubDate where blog.id=:qId")
  void updateBlog(@Param("qTitle") String title, @Param("qUserId") int userId, @Param("qContent") String content,
      @Param("qPubDate") Date pubDate, @Param("qId") int id);
}
