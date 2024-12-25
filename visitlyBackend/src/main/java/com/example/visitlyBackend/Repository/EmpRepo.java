package com.example.visitlyBackend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.visitlyBackend.Model.Employee;

@Repository
public interface EmpRepo extends JpaRepository<Employee, Long> {
    Employee findByEmpName(String empname);
}
