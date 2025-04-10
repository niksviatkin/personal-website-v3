package com.viatkin.portfolio_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests.requestMatchers(
                                        "/",
                                        "/about",
                                        "/resume",
                                        "/projects",
                                        "/projects/**",
                                        "/v1/**",
                                        "/error",
                                        "/css/**", "/js/**", "/images/**", "/favicon.ico",
                                        "/*.png", "/*.jpg", "/*.svg", "/*.pdf"
                                ).permitAll()
                                // .requestMatchers("/admin/**", "/admin/api/**").hasRole("ADMIN")
                                // For now, secure everything as a starting point:
                                .anyRequest().authenticated() // All other requests require authentication
                )
                .formLogin(form -> form
//                        .loginPage("/your-custom-login-page") // If create a custom UI login page
                                .permitAll() // Allow everyone to access the login page itself
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/")
                        .permitAll()
                )
                // Keep HTTP Basic enabled (optional, useful for API testing with Postman)
                .httpBasic(withDefaults())
                // Keep CSRF disabled (suitable for stateless APIs / SPA admin panels)
                .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}