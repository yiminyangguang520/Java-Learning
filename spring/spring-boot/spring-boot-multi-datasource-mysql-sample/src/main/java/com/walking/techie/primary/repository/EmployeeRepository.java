package com.walking.techie.primary.repository;

import com.walking.techie.model.primary.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {}
