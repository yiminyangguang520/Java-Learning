package com.onlinetutorialspoint.repository.db2;

import com.onlinetutorialspoint.model.db2.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author min
 */
@Repository
public interface DepartmentRepository extends CrudRepository<Department, Long> {

}