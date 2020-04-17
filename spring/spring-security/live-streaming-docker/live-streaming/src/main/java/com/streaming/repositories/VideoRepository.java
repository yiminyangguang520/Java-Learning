package com.streaming.repositories;

import com.streaming.domains.Video;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @author min
 */
public interface VideoRepository extends PagingAndSortingRepository<Video, Long> {

  List<Video> findByUserUsername(String user);
}
