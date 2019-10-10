package org.lee.mybatis.service;

import java.util.List;
import org.lee.mybatis.model.Student;


/**
 * @author bruce
 */
public interface StudentService extends BaseService<Student, Integer> {

  List<Student> saveBatch(List<Student> students);

}
