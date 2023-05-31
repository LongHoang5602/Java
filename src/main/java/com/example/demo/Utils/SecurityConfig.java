package com.example.demo.Utils;

import org.springframework.context.annotation.*;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.services.CustomerUserDetailService;

public class SecurityConfig {
        @Bean
        public UserDetailsService userDetailsService() {
                return new CustomerUserDetailService();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        public DaoAuthenticationProvider authenticationProvider() {
                DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
                auth.setUserDetailsService(userDetailsService());
                auth.setPasswordEncoder(passwordEncoder());
                return auth;
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                return http.csrf().disable()
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers("/css/**", "/js/**", "/", "/register",
                                                                "/error")
                                                .permitAll()
                                                .requestMatchers("/books/edit", "/books/delete")
                                                .authenticated()

                                                .requestMatchers("/books", "/books/add")

                                                .authenticated()

                                                .anyRequest().authenticated()

                                )
                                .logout(logout -> logout.logoutUrl("/logout")
                                                .logoutSuccessUrl("/login")
                                                .deleteCookies("JSESSIONID")
                                                .invalidateHttpSession(true)
                                                .clearAuthentication(true)

                                                .permitAll()

                                )
                                .formLogin(formLogin -> formLogin.loginPage("/login")
                                                .loginProcessingUrl("/login")
                                                .defaultSuccessUrl("/")
                                                .permitAll()

                                )
                                .rememberMe(rememberMe -> rememberMe.key("uniqueAndSecret")
                                                .tokenValiditySeconds(86400).userDetailsService(userDetailsService()))
                                .exceptionHandling(exceptionHandling -> exceptionHandling.accessDeniedPage("/403"))
                                .build();
        }
}
