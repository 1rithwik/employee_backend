package com.example.visitlyBackend.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;

import org.springframework.security.core.userdetails.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    // public SecurityFilterChain securityFilterChain(HttpSecurity httpsec) throws
    // Exception {

    // httpsec
    // .csrf(customizer -> customizer.disable())
    // .authorizeHttpRequests(request -> request
    // .requestMatchers("login", "register")
    // .permitAll()
    // .anyRequest().authenticated())
    // .httpBasic(Customizer.withDefaults())
    // .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
    // .sessionManagement(session ->
    // session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));// session
    // // creation policy is stateless i.e every request new session is created

    // return httpsec.build();
    // }
    public SecurityFilterChain securityFilterChain(HttpSecurity httpsec) throws Exception {
        httpsec
                .csrf(customizer -> customizer.disable())
                .authorizeHttpRequests(request -> request
                        .anyRequest().permitAll())
                .httpBasic(Customizer.withDefaults());

        return httpsec.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        provider.setUserDetailsService(userDetailsService);
        return provider;

    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();

    }

}