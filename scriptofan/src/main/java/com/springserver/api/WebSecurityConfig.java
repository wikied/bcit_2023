package com.springserver.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration

@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                .requestMatchers("/test-auth").authenticated()
                .requestMatchers("/role").authenticated()
                .requestMatchers("/role/*").authenticated()
                .requestMatchers("/transaction").authenticated()
                .requestMatchers("/transaction/*").authenticated()
                .requestMatchers("/user/*").authenticated()
                .anyRequest().permitAll()
                .and()
                .logout()
                .and()
                .formLogin()
                .and()
                .csrf().disable();

        return http.build();
    }

}

