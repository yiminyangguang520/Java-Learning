package org.ostenant.springboot.learning.examples.controller;

import org.ostenant.springboot.learning.examples.model.primary.Department;
import org.ostenant.springboot.learning.examples.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DepartmentController implements BasicController<Department, String> {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @RequestMapping(value = "/api/department/{id}", method = RequestMethod.DELETE)
    public int deleteById(@PathVariable("id") String id) {
        return departmentService.deleteById(id);
    }

    @RequestMapping(value = "/api/department", method = RequestMethod.POST)
    public int save(Department department) {
        return departmentService.save(department);
    }

    @RequestMapping(value = "/api/departments", method = RequestMethod.GET)
    public List<Department> findAll() {
        return departmentService.findAll();
    }

    @RequestMapping(value = "/api/department/{id}", method = RequestMethod.GET)
    public Department findById(@PathVariable("id") String id) {
        return departmentService.findById(id);
    }

    @RequestMapping(value = "/api/department", method = RequestMethod.PUT)
    public int update(Department department) {
        return departmentService.update(department);
    }
}
