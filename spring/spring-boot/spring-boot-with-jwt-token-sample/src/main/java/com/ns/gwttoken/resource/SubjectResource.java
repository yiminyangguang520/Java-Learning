package com.ns.gwttoken.resource;

import com.ns.gwttoken.entity.Subject;
import com.ns.gwttoken.repository.SubjectRepository;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author litz-a
 */
@RestController
@RequestMapping("/Subject")
public class SubjectResource {

  private final SubjectRepository subjectRepository;

  public SubjectResource(SubjectRepository subjectRepository) {
    this.subjectRepository = subjectRepository;
  }

  @GetMapping
  @PreAuthorize("hasRole('ADMIN')")
  public List<Subject> getAllSubjects() {
    return subjectRepository.findAll();
  }
}
