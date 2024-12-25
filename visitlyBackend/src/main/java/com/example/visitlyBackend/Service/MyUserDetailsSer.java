package com.example.visitlyBackend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.example.visitlyBackend.Model.Employee;
import com.example.visitlyBackend.Repository.EmpRepo;
import com.example.visitlyBackend.Model.UserPrincipal;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class MyUserDetailsSer implements UserDetailsService {

    @Autowired
    private EmpRepo userRepo;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee user = userRepo.findByEmpName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new UserPrincipal(user);
    }

}
