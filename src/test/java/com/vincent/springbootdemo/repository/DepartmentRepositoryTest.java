package com.vincent.springbootdemo.repository;

import com.vincent.springbootdemo.entity.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DepartmentRepositoryTest {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp() {
        Department department = Department.builder()
                .departmentAddress("Accra")
                .departmentName("CD")
                .departmentCode("CD-001")
                .build();
        entityManager.persist(department);
    }
    @Test
    @DisplayName("Get data base on department id")
    public void whenFindById_thenReturnDepartment(){
        Long departmentId = 1L;
        Department department = departmentRepository.findById(departmentId).get();
        assertEquals(department.getDepartmentName(),"IT");

    }
}