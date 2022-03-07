package com.vincent.springbootdemo.controller;

import com.vincent.springbootdemo.entity.Department;
import com.vincent.springbootdemo.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private DepartmentService departmentService;
    private Department department;

    @BeforeEach
    void setUp() {
        department = Department.builder()
                .departmentCode("MK-001")
                .departmentName("MK")
                .departmentAddress("Accra")
                .departmentId(1L)
                .build();
    }

    @Test
    @Disabled
    void saveDepartment() throws Exception {
        Department inputDepartment = Department.builder()
                .departmentCode("IT-001")
                .departmentName("IT")
                .departmentAddress("Accra")
                .build();
        Mockito.when(departmentService.saveDepartment(inputDepartment))
                .thenReturn(department);
        mockMvc.perform(post("/department")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\t\"departmentName\":\"MK\",\n" +
                        "\t\"departmentAddress\":\"Accra\",\n" +
                        "\t\"departmentCode\":\"MK-001\"\n" +
                        "}"))
                .andExpect(status().isOk());
    }

    @Test
    @Disabled
    void deleteDepartmentById() throws Exception {
        Mockito.when(departmentService.getDepartmentBy(1L))
                .thenReturn(department);
        mockMvc.perform(get("/departments")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.departmentName")
                        .value(department.getDepartmentName()));
    }
}