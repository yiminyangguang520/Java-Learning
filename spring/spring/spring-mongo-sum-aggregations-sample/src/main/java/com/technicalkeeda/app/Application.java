package com.technicalkeeda.app;

import com.technicalkeeda.bean.Car;
import com.technicalkeeda.bean.SalesReport;
import com.technicalkeeda.dao.CarDao;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author min
 */
public class Application {

  public static void main(String[] args) {

    try {
      ApplicationContext context = new ClassPathXmlApplicationContext("spring-beans.xml");

      CarDao carDao = (CarDao) context.getBean("carDao");
      carDao.drop();

      Car polo2014 = new Car();
      polo2014.setBrand("Volkswagen");
      polo2014.setModel("Polo");
      polo2014.setNumberOfCars(59600);
      polo2014.setSalesyear(2014);
      carDao.create(polo2014);

      Car polo2015 = new Car();
      polo2015.setBrand("Volkswagen");
      polo2015.setModel("Polo");
      polo2015.setNumberOfCars(29010);
      polo2015.setSalesyear(2015);
      carDao.create(polo2015);

      Car jetta2014 = new Car();
      jetta2014.setBrand("Volkswagen");
      jetta2014.setModel("Jetta");
      jetta2014.setNumberOfCars(25000);
      jetta2014.setSalesyear(2014);
      carDao.create(jetta2014);

      Car jetta2015 = new Car();
      jetta2015.setBrand("Volkswagen");
      jetta2015.setModel("Jetta");
      jetta2015.setNumberOfCars(16200);
      jetta2015.setSalesyear(2015);
      carDao.create(jetta2015);

      Car swift2014 = new Car();
      swift2014.setBrand("Maruti Suzuki");
      swift2014.setModel("Swift");
      swift2014.setNumberOfCars(168000);
      swift2014.setSalesyear(2014);
      carDao.create(swift2014);

      Car swift2015 = new Car();
      swift2015.setBrand("Maruti Suzuki");
      swift2015.setModel("Swift");
      swift2015.setNumberOfCars(118000);
      swift2015.setSalesyear(2015);
      carDao.create(swift2015);

      Car ertiga2014 = new Car();
      ertiga2014.setBrand("Maruti Suzuki");
      ertiga2014.setModel("Ertiga");
      ertiga2014.setNumberOfCars(80000);
      ertiga2014.setSalesyear(2014);
      carDao.create(ertiga2014);

      Car ertiga2015 = new Car();
      ertiga2015.setBrand("Maruti Suzuki");
      ertiga2015.setModel("Ertiga");
      ertiga2015.setNumberOfCars(42000);
      ertiga2015.setSalesyear(2015);
      carDao.create(ertiga2015);

      Car i202014 = new Car();
      i202014.setBrand("Hyundai");
      i202014.setModel("i20");
      i202014.setNumberOfCars(45000);
      i202014.setSalesyear(2014);
      carDao.create(i202014);

      Car i202015 = new Car();
      i202015.setBrand("Hyundai");
      i202015.setModel("i20");
      i202015.setNumberOfCars(19000);
      i202015.setSalesyear(2015);
      carDao.create(i202015);

      Car i102014 = new Car();
      i102014.setBrand("Hyundai");
      i102014.setModel("i10");
      i102014.setNumberOfCars(95000);
      i102014.setSalesyear(2014);
      carDao.create(i102014);

      Car i102015 = new Car();
      i102015.setBrand("Hyundai");
      i102015.setModel("i10");
      i102015.setNumberOfCars(55000);
      i102015.setSalesyear(2014);
      carDao.create(i102015);

      System.out.println("--------------Sales Report Both Year ---------------------------");

      List<SalesReport> all = carDao.aggregationByAll();

      for (SalesReport salesReport : all) {
        System.out.println(salesReport);
      }

      System.out.println("--------------Sales Report 2014-------------------------------");

      List<SalesReport> byYear = carDao.aggregationByYear(2014);

      for (SalesReport salesReport : byYear) {
        System.out.println(salesReport);
      }

    } catch (Exception e) {
      System.out.println("Exception Application.java" + e.getMessage());
      e.printStackTrace();

    }

  }
}