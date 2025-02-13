package com.persist.chartable.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


        @Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.cors(AbstractHttpConfigurer::disable).csrf(AbstractHttpConfigurer::disable)
				//.exceptionHandling((exception) -> exception.authenticationEntryPoint(new JwtAuthEntryPoint()))
				.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(auth -> auth.requestMatchers("/podcaster-register", "/podcaster-login", "/", "/index", "/signin", "/css/**",
                                "/js/**", "/**", "/discover", "/analytics").permitAll()
						.anyRequest().authenticated());

		//http.addFilterBefore(new JwtAuthFilter(), UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
//     @Bean
//     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//         http
//                 .authorizeHttpRequests(auth -> auth
//                         .requestMatchers("/podcaster-register", "/podcaster-login", "/", "/index", "/signin", "/css/**",
//                                 "/js/**", "/**", "/discover", "/analytics")
//                         .permitAll()
//                         .anyRequest().authenticated())
//                 .csrf(csrf -> csrf.disable());

//         return http.build();
//     }
}
