package org.ostenant.springboot.learning.examples.repository.primary;

import org.ostenant.springboot.learning.examples.model.primary.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaDepartmentRepository extends JpaRepository<Department, String> {
}
