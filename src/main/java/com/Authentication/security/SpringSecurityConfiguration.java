package com.Authentication.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration {
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity.authorizeHttpRequests(request->{
        request.requestMatchers("/home").permitAll();
        request.requestMatchers("/admin/**").hasRole("ADMIN");
        request.requestMatchers("/user/**").hasRole("USER");
        request.anyRequest().authenticated();}).build();
//this will not work 
//we have overrided default settings 
//other than these three request provided rest will not work 

    }

    // in memory authentication
    // https://bcrypt-generator.com/ for online becrypt password encoder
    //1234 password
    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails normalUser=User.builder()
                                    .username("user")
                                    .password("$2a$12$WgamfuC2oeg1tKiM4p/u6OFA99ffketrd9Qgj9l1z84ukpZy2hLaq")
                                    .roles("USER")
                                    .build();

        UserDetails adminUser=User.builder()
                                    .username("admin")
                                    .password("$2a$12$WgamfuC2oeg1tKiM4p/u6OFA99ffketrd9Qgj9l1z84ukpZy2hLaq")
                                    .roles("USER","ADMIN")
                                    .build();

        return new InMemoryUserDetailsManager(normalUser,adminUser);
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }
}
