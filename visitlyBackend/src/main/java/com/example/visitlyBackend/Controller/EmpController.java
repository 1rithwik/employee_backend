package com.example.visitlyBackend.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.visitlyBackend.Model.Employee;
import com.example.visitlyBackend.Service.EmpService;

@RestController
// @CrossOrigin(origins = "http://localhost:4200")
public class EmpController {

    private EmpService empService;
    HashMap<String, Employee> map = new HashMap<>();

    @Autowired
    public EmpController(EmpService empService) {
        this.empService = empService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Employee employee) {
        return ResponseEntity.ok(empService.register(employee));
    }

    @PostMapping("/login")
    public HashMap<String, Employee> login(@RequestBody Employee user) {
        map.clear();
        String token = empService.verify(user);
        if (token != null) {
            Employee userDetails = empService.getUserDetails(user.getEmpName());
            map.put(token, userDetails);
        } else {
            return null;
        }
        return map;
    }

    @PutMapping("/LogInToWork")
    public ResponseEntity<?> signIn(@RequestBody Employee user) {
        empService.signInTOWork(user);
        return ResponseEntity.ok("Signed in");
    }

    @PutMapping("/LogOutFromWork")
    public ResponseEntity<?> signOut(@RequestHeader("Authorization") @RequestBody Employee user) {
        empService.signOutFromWork(user);
        return ResponseEntity.ok("Signed out");
    }

    @PutMapping("/editDetails")
    public ResponseEntity<?> editDetails(@RequestBody Employee payload) {

        // Employee curEmp = repo.findById(payload.getEmployeeId())
        // .orElseThrow(() -> new RuntimeException("Employee not found"));

        empService.editEmail(payload.getEmployeeId(), payload.getEmpEmail());
        empService.editPhoneNumber(payload.getEmployeeId(), payload.getEmpPhone());
        return ResponseEntity.ok("Updated successfully");
    }
}

// @NoArgsConstructor
// @Getter
// @Setter
// class EditEmp {
// private String phoneNumber;
// private String email;
// private Long userId;
// }