package com.vincent.springbootdemo.controller;

import com.vincent.springbootdemo.entity.Department;
import com.vincent.springbootdemo.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;
    private final Logger LOGGER =
            LoggerFactory.getLogger(DepartmentController.class);

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping("/department")
    public Department saveDepartment(@Valid @RequestBody Department department){
        LOGGER.info("created " + department);
        return departmentService.saveDepartment(department);
    }

    @GetMapping("/departments")
    public List<Department> getDepartments(){
        return departmentService.getDepartments();
    }

    @GetMapping("/department/{departmentId}")
    public Department getDepartmentById(@PathVariable("departmentId") Long departmentId) throws Exception {
        LOGGER.info("got department with id: " + departmentId);
        return departmentService.getDepartmentBy(departmentId);
    }
    @DeleteMapping("/department/{departmentId}/delete")
    public Department deleteDepartmentById(@PathVariable("departmentId") Long departmentId) throws Exception {
        return departmentService.deleteDepartmentBy(departmentId);
    }

    @PutMapping("/department/{departmentId}/update")
    public Department updateDepartment(@PathVariable("departmentId") Long departmentId,@RequestBody Department department) throws Exception {
        LOGGER.info("updated department with Id: " + department + " to " + department);
        return departmentService.updateDepartment(departmentId,department);
    }

    @GetMapping("/department/name/{departmentName}")
    public Department getDepartmentByName(@PathVariable("departmentName") String departmentName) throws Exception {
        return departmentService.getDepartmentByName(departmentName);
    }


}
