package com.project.start.abysses.config;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.project.start.abysses.filter.JWTTokenGeneratorFilter;
import com.project.start.abysses.filter.JWTTokenValidatorFilter;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
    	
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

        .cors(corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {
        	
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration config = new CorsConfiguration();
                config.setAllowedOrigins(Collections.singletonList("http://localhost:18757")); //TO BE UPDATE SELON LE PORT du front
                config.setAllowedMethods(Collections.singletonList("*"));
                config.setAllowCredentials(true);
                config.setAllowedHeaders(Collections.singletonList("*"));  
                config.setMaxAge(3600L);
                return config;
            }
        }))
        
        .csrf((csrf) -> csrf.disable())

        .addFilterAfter(new JWTTokenGeneratorFilter(), BasicAuthenticationFilter.class)
        .addFilterBefore(new JWTTokenValidatorFilter(), BasicAuthenticationFilter.class)
        
        .authorizeHttpRequests((requests) -> requests

        		.requestMatchers("/api/users/**").hasAnyRole("USER", "ADMIN")
        		.requestMatchers("/api/user/**").hasAnyRole("USER", "ADMIN")
                .requestMatchers("/api/register").permitAll())
        
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        
        return http.build();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
}