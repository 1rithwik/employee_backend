package com.example.visitlyBackend.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.visitlyBackend.Model.Employee;
import com.example.visitlyBackend.Service.EmpService;
import com.example.visitlyBackend.Repository.EmpRepo;
import com.example.visitlyBackend.Exceptions.NoUser;
import com.example.visitlyBackend.Exceptions.InvalidInput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class EmpServiceTest {

    @Mock
    private EmpRepo empRepo;

    @InjectMocks
    private EmpService empService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegister() {
        Employee employee = new Employee();
        employee.setEmpPassword("password");

        when(empRepo.save(any(Employee.class))).thenReturn(employee);

        Employee savedEmployee = empService.register(employee);
        assertNotNull(savedEmployee);
    }

    @Test
    public void testSignInToWork() {
        Employee employee = new Employee();
        employee.setEmployeeId(1L);

        when(empRepo.findById(1L)).thenReturn(Optional.of(employee));

        empService.signInTOWork(employee);

        verify(empRepo, times(1)).save(employee);
    }

    @Test
    public void testSignOutFromWork() {
        Employee employee = new Employee();
        employee.setEmployeeId(1L);
        employee.setLastLoginTime(java.time.LocalDateTime.now().minusHours(5));

        when(empRepo.findById(1L)).thenReturn(Optional.of(employee));

        empService.signOutFromWork(employee);

        verify(empRepo, times(1)).save(employee);
    }

    @Test
    public void testEditPhoneNumberInvalid() {
        assertThrows(NoUser.class, () -> empService.editPhoneNumber(1L, "123"));
    }

    @Test
    public void testEditEmailInvalid() {
        assertThrows(NoUser.class, () -> empService.editEmail(1L, "invalidEmail"));
    }
}
