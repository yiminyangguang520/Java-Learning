package com.technicalkeeda.dao;

import com.technicalkeeda.bean.Car;
import com.technicalkeeda.bean.SalesReport;
import java.util.List;

/**
 * @author min
 */
public interface CarDao {

  List<SalesReport> aggregationByAll();

  List<SalesReport> aggregationByYear(int year);

  void create(Car car);

  void drop();
}
