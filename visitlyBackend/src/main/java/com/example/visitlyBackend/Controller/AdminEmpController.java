package com.example.visitlyBackend.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.visitlyBackend.Service.EmpService;

@RestController
// @CrossOrigin(origins = "http://localhost:4200")
public class AdminEmpController {

    private EmpService empService;

    public AdminEmpController(EmpService empService) {
        this.empService = empService;
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        empService.deleteEmployeeById(id);
        return ResponseEntity.ok("Employee deleted successfully");
    }

    @GetMapping("/employees")
    public ResponseEntity<?> getAllEmployees() {
        return ResponseEntity.ok(empService.getAllEmployees());
    }

}
