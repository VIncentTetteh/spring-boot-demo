package com.vincent.springbootdemo.service;

import com.vincent.springbootdemo.entity.Department;

import java.util.List;

public interface DepartmentService {
    Department saveDepartment(Department department);

    List<Department> getDepartments();

    Department getDepartmentBy(Long departmentId) throws Exception;

    Department deleteDepartmentBy(Long departmentId) throws Exception;

    Department updateDepartment(Long departmentId, Department department) throws Exception;

    Department getDepartmentByName(String departmentName) throws Exception;
}
