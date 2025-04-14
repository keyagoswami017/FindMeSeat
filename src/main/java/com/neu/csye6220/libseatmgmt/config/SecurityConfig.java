package com.neu.csye6220.libseatmgmt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // allow form POST without token
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login","/doLogin" ,"/register","/register/**", "/css/**", "/js/**", "/images/**","/WEB-INF/views/**",
                         "/user-Home","/admin-Home","/logout","/update","/profile").permitAll()
                        .anyRequest().authenticated()
                );
        return http.build();
    }
}
