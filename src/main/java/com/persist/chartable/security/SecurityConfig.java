package com.persist.chartable.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

//    @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//         http
//             .csrf().disable() // Disable CSRF for development
//             .authorizeHttpRequests((auth) -> auth
//                 .requestMatchers("/", "/index", "/css/**", "/js/**", "/images/**", "/api/**").permitAll() // Public access
//                 .anyRequest().authenticated() // Secure other endpoints
//             )
//             .formLogin().disable(); // Disable default login form
//         return http.build();
//     }
@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/index", "/signin", "/css/**", "/js/**","/**","/discover","/analytics").permitAll()
                .anyRequest().authenticated()
            )
            .csrf(csrf -> csrf.disable());
        
        return http.build();
    }
}
