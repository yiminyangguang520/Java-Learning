package com.packtpub.springsecurity.repository;

import com.packtpub.springsecurity.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author bruce
 */
public interface EventRepository extends JpaRepository<Event, Integer> {

}
