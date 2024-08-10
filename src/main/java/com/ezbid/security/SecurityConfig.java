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
                        authorizeRequests
                                .requestMatchers("/public/**").permitAll()  // Public access to specific paths
                                .anyRequest().authenticated()  // Authenticate all other requests
                )
                .formLogin(formLogin ->  // Custom form login configuration
                        formLogin
                                .loginPage("/login")  // Custom login page URL
                                .permitAll()  // Allow everyone to access the login page
                )
                .logout(logout ->  // Custom logout configuration
                        logout
                                .logoutUrl("/logout")  // Custom logout URL
                                .logoutSuccessUrl("/")  // Redirect after successful logout
                );

        return http.build();
    }
}
