package com.javainterviewpoint;

import com.javainterviewpoint.entity.Employee;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class EmployeeRestTemplateClient {

  public static final String REST_BASE_URI = "http://localhost:8080";

  static RestTemplate restTemplate = new RestTemplate();

  /**
   * POST
   **/
  public static void createEmployee() {
    Employee employee = new Employee();
    employee.setId(5);
    employee.setName("JIP5");
    employee.setAge(50);
    employee.setDept("Blogging");

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    // headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

    HttpEntity entity = new HttpEntity<>(employee, headers);
    restTemplate.postForObject(REST_BASE_URI + "/employee", entity, Employee.class);
  }

  /**
   * GET
   **/
  private static void getEmployee(int id) {
    Employee employee = restTemplate.getForObject(REST_BASE_URI + "/employee/" + id, Employee.class);
    System.out.println("**** Employee with id : " + id + "****");
    System.out
        .println("Id :" + employee.getId() + "    Name : " + employee.getName() + "   Dept : " + employee.getDept() + "   Age : " + employee.getAge());
  }

  public static void getAllEmployees() {
    List<Map<String, Object>> employeeList = restTemplate.getForObject(REST_BASE_URI + "/employees", List.class);
    if (employeeList != null) {
      System.out.println("**** All Employees ****");
      for (Map<String, Object> map : employeeList) {
        System.out.println(
            "Id : id=" + map.get("id") + "   Name=" + map.get("name") + "   Dept : " + map.get("dept") + "   Age=" + map.get("age"));
      }
    } else {
      System.out.println("No Employee exist!!");
    }
  }

  /**
   * PUT
   **/
  public static void updateEmployee() {
    Employee employee = new Employee();
    employee.setId(5);
    employee.setName("JIP5555");
    employee.setAge(5555);
    employee.setDept("Blogging");

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity entity = new HttpEntity<>(employee, headers);

    restTemplate.put(REST_BASE_URI + "/employee", entity, Employee.class);
  }

  /**
   * DELETE
   **/
  public static void deleteEmployee(int id) {
    restTemplate.delete(REST_BASE_URI + "/employee/" + id);
  }

  public static void main(String args[]) {
    createEmployee();

    getAllEmployees();

    getEmployee(2);

    updateEmployee();

    deleteEmployee(5);
  }
}
