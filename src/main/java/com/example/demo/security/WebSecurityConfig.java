/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.security;

import com.example.demo.security.service.UserDetailsServiceImpl;
import jakarta.servlet.http.HttpSessionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 *
 * @author marco-romero
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    public WebSecurityConfig(UserDetailsServiceImpl userDetailsServiceImpl) {
//        this.userDetailsServiceImpl = userDetailsServiceImpl;
//    }
    String[] resources = {
        "/vendor/**", "/scss/**", "/include/**", "/plugins/**",
        "/css/**", "/icons/**", "/img/**", "/js/**", "/layer/**"
    };

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
    @Bean
    public HttpSessionListener getHttpSessionListener() {
        return new SessionListener();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .cors(cors -> cors.disable())
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                        "/login",
                        "/login?expired=true",
                        "/demo/login",
                        "/demo/login?expired=true"
                // Swagger
                //                        "/v3/api-docs/**",
                //                        "/swagger-ui/**",
                //                        "/swagger-ui.html"
                ).permitAll()
                .requestMatchers(resources).permitAll()
                .anyRequest().authenticated()
                )
                .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/demo", true)
                .failureUrl("/login?error=true")
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll()
                )
                .logout(logout -> logout
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true)
                .permitAll()
                )
                .exceptionHandling(exception -> exception
                .authenticationEntryPoint(
                        new AjaxAwareAuthenticationEntryPoint("/login")
                )
                );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            HttpSecurity http) throws Exception {

        AuthenticationManagerBuilder builder
                = http.getSharedObject(AuthenticationManagerBuilder.class);

        builder
                .userDetailsService(userDetailsServiceImpl)
                .passwordEncoder(passwordEncoder);

        return builder.build();
    }

}
