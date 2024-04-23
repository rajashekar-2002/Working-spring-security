package com.Authentication.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SpringSecurityConfiguration {
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity.authorizeHttpRequests(request->{
        request.requestMatchers("/home").permitAll();
        request.requestMatchers("/admin/**").hasRole("ADMIN");
        request.requestMatchers("/user/**").hasRole("USER");
        request.anyRequest().authenticated();}).build();

    
    }
}
