package com.example.polls.repository;

import com.example.polls.model.Poll;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author rajeevkumarsingh
 * @date 20/11/17
 */
@Repository
public interface PollRepository extends JpaRepository<Poll, Long> {

  /**
   * findById
   * @param pollId
   * @return
   */
  @Override
  Optional<Poll> findById(Long pollId);

  /**
   * findByCreatedBy
   * @param userId
   * @param pageable
   * @return
   */
  Page<Poll> findByCreatedBy(Long userId, Pageable pageable);

  /**
   * countByCreatedBy
   * @param userId
   * @return
   */
  long countByCreatedBy(Long userId);

  /**
   * findByIdIn
   * @param pollIds
   * @return
   */
  List<Poll> findByIdIn(List<Long> pollIds);

  /**
   * findByIdIn
   * @param pollIds
   * @param sort
   * @return
   */
  List<Poll> findByIdIn(List<Long> pollIds, Sort sort);
}
