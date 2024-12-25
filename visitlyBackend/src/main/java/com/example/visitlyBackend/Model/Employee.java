package com.example.visitlyBackend.Model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "employee")
@NoArgsConstructor
@Getter
@Setter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    private String empName;
    private String empEmail;
    private String empPassword;
    private String empPhone;
    private LocalDateTime lastLoginTime;
    private String loginStatus;
    private int lateAttendCount;
    private int earlyLeaveCount;
    private String role = "USER";

    @PrePersist
    public void setLateAttendCountToZero() {
        this.lateAttendCount = 0;
        this.earlyLeaveCount = 0;
        this.loginStatus = "false";
    }

}
