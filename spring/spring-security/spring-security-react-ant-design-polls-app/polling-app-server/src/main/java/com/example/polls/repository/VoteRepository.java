package com.example.polls.repository;

import com.example.polls.model.ChoiceVoteCount;
import com.example.polls.model.Vote;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author litz-a
 */
@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

  /**
   * countByPollIdInGroupByChoiceId
   * @param pollIds
   * @return
   */
  @Query("SELECT NEW com.example.polls.model.ChoiceVoteCount(v.choice.id, count(v.id)) FROM Vote v WHERE v.poll.id in :pollIds GROUP BY v.choice.id")
  List<ChoiceVoteCount> countByPollIdInGroupByChoiceId(@Param("pollIds") List<Long> pollIds);

  /**
   * countByPollIdGroupByChoiceId
   * @param pollId
   * @return
   */
  @Query("SELECT NEW com.example.polls.model.ChoiceVoteCount(v.choice.id, count(v.id)) FROM Vote v WHERE v.poll.id = :pollId GROUP BY v.choice.id")
  List<ChoiceVoteCount> countByPollIdGroupByChoiceId(@Param("pollId") Long pollId);

  /**
   * findByUserIdAndPollIdIn
   * @param userId
   * @param pollIds
   * @return
   */
  @Query("SELECT v FROM Vote v where v.user.id = :userId and v.poll.id in :pollIds")
  List<Vote> findByUserIdAndPollIdIn(@Param("userId") Long userId, @Param("pollIds") List<Long> pollIds);

  /**
   * findByUserIdAndPollId
   * @param userId
   * @param pollId
   * @return
   */
  @Query("SELECT v FROM Vote v where v.user.id = :userId and v.poll.id = :pollId")
  Vote findByUserIdAndPollId(@Param("userId") Long userId, @Param("pollId") Long pollId);

  /**
   * countByUserId
   * @param userId
   * @return
   */
  @Query("SELECT COUNT(v.id) from Vote v where v.user.id = :userId")
  long countByUserId(@Param("userId") Long userId);

  /**
   * findVotedPollIdsByUserId
   * @param userId
   * @param pageable
   * @return
   */
  @Query("SELECT v.poll.id FROM Vote v WHERE v.user.id = :userId")
  Page<Long> findVotedPollIdsByUserId(@Param("userId") Long userId, Pageable pageable);
}

