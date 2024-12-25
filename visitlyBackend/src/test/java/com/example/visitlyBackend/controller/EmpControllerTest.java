package com.example.visitlyBackend.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.visitlyBackend.Model.Employee;
import com.example.visitlyBackend.Service.EmpService;
import com.example.visitlyBackend.Controller.EmpController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EmpControllerTest {

    @Mock
    private EmpService empService;

    @InjectMocks
    private EmpController empController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(empController).build();
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void testRegister() throws Exception {
        Employee employee = new Employee();
        employee.setEmpName("John");
        employee.setEmpEmail("john@example.com");
        employee.setEmpPassword("password");

        when(empService.register(any(Employee.class))).thenReturn(employee);

        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isOk());
    }

    @Test
    public void testLogin() throws Exception {
        Employee employee = new Employee();
        employee.setEmpName("John");
        employee.setEmpPassword("password");

        when(empService.verify(any(Employee.class))).thenReturn("token");

        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isOk());
    }
}
