package com.auth0.samples.authapi.springbootauthupdated.task.repository;

import com.auth0.samples.authapi.springbootauthupdated.task.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author litz-a
 */
public interface TaskRepository extends JpaRepository<Task, Long> {

}