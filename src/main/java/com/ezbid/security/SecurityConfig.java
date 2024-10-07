package com.ezbid.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests.anyRequest().permitAll()  // Permit all requests
                )
                .csrf(csrf -> csrf.disable())  // Disable CSRF protection
                .formLogin(formLogin -> formLogin.disable())  // Disable form login
                .logout(logout -> logout.disable());  // Disable logout

        return http.build();
    }
}
