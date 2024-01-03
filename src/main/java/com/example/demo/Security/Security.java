package com.example.demo.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class Security {

	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .anyRequest().permitAll() // Allow unrestricted access to all endpoints
            .and()
            .csrf().disable() // Disable CSRF for simplicity, consider enabling in production with proper configuration
            .httpBasic(); // Use HTTP Basic authentication

        return http.build();
    }
}