package com.viatkin.portfolio_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration // Marks this class as a source of bean definitions
@EnableWebSecurity // Enables Spring Security's web security support
public class SecurityConfig {

    @Bean // Defines a bean that Spring manages
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                // Allow unauthenticated access to API endpoints under /v1/**
                                .requestMatchers("/v1/**").permitAll()
                                // Require authentication for any other request (if any exist later)
                                .anyRequest().authenticated()
                )
                // You might keep defaults for login forms/http basic if other parts need them later
                .formLogin(withDefaults()) // Example: using default form login config
                .httpBasic(withDefaults()) // Example: using default http basic config

        // If using permitAll() for specific paths, CSRF protection is usually still good to have enabled.
        // Only disable CSRF if absolutely necessary & you understand the implications,
        // often needed for stateless APIs or if handling CSRF differently.
                .csrf(AbstractHttpConfigurer::disable);


        return http.build();
    }
}