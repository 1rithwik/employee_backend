package com.example.visitlyBackend.model;

import com.example.visitlyBackend.Model.Employee;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class EmployeeTest {

    @Test
    public void testDefaultValuesOnPersist() {
        Employee employee = new Employee();
        employee.setEmpName("John Doe");
        employee.setEmpEmail("john.doe@example.com");

        employee.setLateAttendCountToZero(); // Simulate @PrePersist behavior

        assertEquals(0, employee.getLateAttendCount());
        assertEquals(0, employee.getEarlyLeaveCount());
        assertEquals("false", employee.getLoginStatus());
    }
}
