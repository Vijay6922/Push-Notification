package com.iminbus.pushnotification.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    JwtAuthConverter jwtAuthConverter;

    // Define the security filter chain
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .cors() // Configure CORS
                .and()
                // Configure authorization rules for specific endpoints
                .authorizeHttpRequests(auth -> auth

                        .requestMatchers("/notification").authenticated()
                        .requestMatchers("/**").permitAll()) // Permit access to all other endpoints
                // Configure OAuth2 resource server with JWT authentication
                .oauth2ResourceServer(t -> t.jwt(Configurer -> Configurer.jwtAuthenticationConverter(jwtAuthConverter))).build();
    }

  
}
