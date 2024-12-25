package com.example.visitlyBackend.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.visitlyBackend.Model.Employee;
import com.example.visitlyBackend.Repository.EmpRepo;
import com.example.visitlyBackend.Exceptions.NoUser;
import com.example.visitlyBackend.Exceptions.InvalidInput;

@Service
public class EmpService {

    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthenticationManager authManager;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    private EmpRepo repo;

    @Autowired
    public EmpService(EmpRepo repo) {
        this.repo = repo;
    }

    public Employee register(Employee emp) {
        emp.setEmpPassword(encoder.encode(emp.getEmpPassword()));
        repo.save(emp);
        return emp;
    }

    public String verify(Employee user) {
        Authentication authentication = authManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getEmpName(), user.getEmpPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getEmpName());
        } else {
            return null;
        }
    }

    public Employee getUserDetails(String username) {
        return repo.findByEmpName(username);
    }

    public void signInTOWork(Employee emp) {
        Long empId = emp.getEmployeeId();
        Employee curEmp = repo.findById(empId).orElseThrow(() -> new NoUser("User is not present"));
        LocalDateTime currLocalDateTime = LocalDateTime.now();
        curEmp.setLastLoginTime(currLocalDateTime);

        curEmp.setLoginStatus("true");

        LocalDateTime nineAM = LocalDate.now().atTime(LocalTime.of(9, 0));
        if (currLocalDateTime.isAfter(nineAM)) {
            curEmp.setLateAttendCount(curEmp.getLateAttendCount() + 1);
        }
        repo.save(curEmp);
    }

    public void signOutFromWork(Employee emp) {
        Long empId = emp.getEmployeeId();
        Employee curEmp = repo.findById(empId).orElseThrow(() -> new NoUser("User is not present"));
        LocalDateTime currLocalDateTime = LocalDateTime.now();
        LocalDateTime lastLoginTime = curEmp.getLastLoginTime();
        // Calculating the duration between last login time and current time
        long hoursWorked = java.time.Duration.between(lastLoginTime, currLocalDateTime).toHours();

        // Checking if the employee worked less than 9 hours
        if (hoursWorked < 9) {
            curEmp.setEarlyLeaveCount(curEmp.getEarlyLeaveCount() + 1);
        }

        // Set login status to false
        curEmp.setLoginStatus("false");
        repo.save(curEmp);
    }

    public void editPhoneNumber(Long userId, String newPhoneNumber) {
        Employee emp = repo.findById(userId)
                .orElseThrow(() -> new NoUser("User is not present"));
        if (newPhoneNumber.length() != 10) {
            throw new InvalidInput("Invalid Phone Number");
        }
        emp.setEmpPhone(newPhoneNumber);
        System.out.println("phoneeeeeeeeeee chhangeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
        System.out.println(emp.getEmpPhone());
        repo.save(emp);
    }

    public void editEmail(Long userId, String newEmail) {
        Employee emp = repo.findById(userId)
                .orElseThrow(() -> new NoUser("User is not present"));
        if (!newEmail.endsWith("@gmail.com")) {
            throw new InvalidInput("Email is not valid");
        }
        emp.setEmpEmail(newEmail);
        System.out.println(emp.getEmpEmail());
        repo.save(emp);
    }

    public void deleteEmployeeById(Long id) {
        Employee emp = repo.findById(id)
                .orElseThrow(() -> new NoUser("User is not present"));
        repo.delete(emp);
    }

    public List<Employee> getAllEmployees() {
        return repo.findAll();
    }
}
