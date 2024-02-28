package com.codecraftery.Code.craftery.server.side.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author Natasa Todorov Markovic
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth ->{
                    auth.requestMatchers("/api/blogs","/api/blogs/*","api/projects","api/projects/*","api/categories","api/categories/*").permitAll();
                    auth.requestMatchers("api/user").hasRole("USER");
                    auth.requestMatchers("api/admin").hasRole("ADMIN");

                }).httpBasic(Customizer.withDefaults())
                .build();
    }


}


