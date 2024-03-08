package com.codecraftery.Code.craftery.server.side.config;

import com.codecraftery.Code.craftery.server.side.service.impl.JpaUserDetailService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class SecurityConfig {

    private final JpaUserDetailService userDetailService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/api/blogs", "/api/blogs/*", "api/projects", "api/projects/*", "api/categories", "api/categories/*", "api/smt").permitAll();
                    auth.anyRequest().authenticated();
                })
                .httpBasic(Customizer.withDefaults())
                .build();
    }


}


