package org.lee.mybatis.repository.primary;

import org.lee.mybatis.model.primary.Department;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author bruce
 */
public interface JpaDepartmentRepository extends JpaRepository<Department, String> {

}
