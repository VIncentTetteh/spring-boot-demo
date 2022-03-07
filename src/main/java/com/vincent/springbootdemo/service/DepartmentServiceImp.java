package com.vincent.springbootdemo.service;

import com.vincent.springbootdemo.entity.Department;
import com.vincent.springbootdemo.error.DepartmentNotFoundException;
import com.vincent.springbootdemo.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class DepartmentServiceImp implements DepartmentService {
    @Autowired
    private DepartmentRepository repository;

    public DepartmentServiceImp(DepartmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Department saveDepartment(Department department) {
        return repository.save(department);
    }

    @Override
    public List<Department> getDepartments() {
        return repository.findAll();
    }

    @Override
    public Department getDepartmentBy(Long departmentId) throws Exception {
        return repository.findById(departmentId).orElseThrow(() -> new DepartmentNotFoundException("department not found"));
    }

    @Override
    public Department deleteDepartmentBy(Long departmentId) throws Exception {
        Department department = getDepartmentBy(departmentId);
        repository.deleteById(department.getDepartmentId());
        return department;
    }

    @Override
    public Department updateDepartment(Long departmentId, Department department) throws Exception {
        Department department1 = getDepartmentBy(departmentId);
        if (Objects.nonNull(department.getDepartmentName()) && !"".equals(department.getDepartmentName())) {
            department1.setDepartmentName(department.getDepartmentName());
        }
        if (Objects.nonNull(department.getDepartmentAddress()) && !"".equals(department.getDepartmentAddress())) {
            department1.setDepartmentAddress(department.getDepartmentAddress());
        }
        if (Objects.nonNull(department.getDepartmentCode()) && !"".equals(department.getDepartmentCode())) {
            department1.setDepartmentCode(department.getDepartmentCode());
        }
        return repository.save(department1);
    }

    @Override
    public Department getDepartmentByName(String departmentName) throws Exception {
        return repository.findByDepartmentNameIgnoreCase(departmentName).orElseThrow(() -> new DepartmentNotFoundException("departmentName not found"));
    }
}
