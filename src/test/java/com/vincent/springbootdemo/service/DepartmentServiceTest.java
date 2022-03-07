package com.vincent.springbootdemo.service;

import com.vincent.springbootdemo.entity.Department;
import com.vincent.springbootdemo.repository.DepartmentRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepartmentServiceTest {
    @Autowired
    private DepartmentService departmentService;
    @MockBean
    private DepartmentRepository departmentRepository;

    @BeforeEach
    void setUp() {
        Department department = Department.builder()
                .departmentName("IT")
                .departmentCode("IT-001")
                .departmentAddress("Accra")
                .departmentId(1L)
                .build();
        Mockito.when(departmentRepository.findByDepartmentNameIgnoreCase("IT"))
                .thenReturn(Optional.ofNullable(department));
        Mockito.when((departmentRepository.findById(1L)))
                .thenReturn(Optional.ofNullable(department));
        List<Department> departmentList = List.of(
                Department.builder().departmentName("IT").build(),
                Department.builder().departmentName("Mk").build()
        );

        Mockito.when(departmentRepository.findAll())
                .thenReturn(departmentList);

    }

    @Test
    @DisplayName("Get Data Base On Valid Department Name")
//    @Disabled
    public void whenValidDepartmentName_thenDepartmentShouldBeFound() throws Exception {
        String departmentName = "IT";
        Department found = departmentService.getDepartmentByName(departmentName);
        assertEquals(departmentName,found.getDepartmentName());
    }

    @Test
    @DisplayName("Get Data Base On Valid Department Id")
    public void whenValidDepartmentId_thenDepartmentShouldBeFound() throws Exception {
        Long departmentId = 1L;
        Department department = departmentService.getDepartmentBy(departmentId);
        assertEquals(departmentId,department.getDepartmentId());

    }

    @Test
    @DisplayName("Get List of Department ")
    public void whenDepartmentAddedToList_thenShouldGetAllDepartments(){
        List<Department> departments = List.of(
                Department.builder().departmentName("IT").build(),
                Department.builder().departmentName("Mk").build()
        );
        List<Department> departmentsFromRepository = departmentService.getDepartments();
        assertEquals(departments,departmentsFromRepository);
    }
}