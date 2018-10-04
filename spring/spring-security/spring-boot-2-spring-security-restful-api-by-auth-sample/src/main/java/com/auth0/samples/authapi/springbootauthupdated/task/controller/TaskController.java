package com.auth0.samples.authapi.springbootauthupdated.task.controller;

import com.auth0.samples.authapi.springbootauthupdated.task.repository.TaskRepository;
import com.auth0.samples.authapi.springbootauthupdated.task.model.Task;
import java.util.List;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author litz-a
 */
@RestController
@RequestMapping("/tasks")
public class TaskController {

  private TaskRepository taskRepository;

  public TaskController(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  @PostMapping
  public void addTask(@RequestBody Task task) {
    taskRepository.save(task);
  }

  @GetMapping
  public List<Task> getTasks() {
    return taskRepository.findAll();
  }

  @PutMapping("/{id}")
  public void editTask(@PathVariable long id, @RequestBody Task task) {
    Task existingTask = taskRepository.findById(id).get();
    Assert.notNull(existingTask, "Task not found");
    existingTask.setDescription(task.getDescription());
    taskRepository.save(existingTask);
  }

  @DeleteMapping("/{id}")
  public void deleteTask(@PathVariable long id) {
    Task taskToDel = taskRepository.findById(id).get();
    taskRepository.delete(taskToDel);
  }
}