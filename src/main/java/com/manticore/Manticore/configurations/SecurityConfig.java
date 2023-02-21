package com.manticore.Manticore.configurations;

import com.manticore.Manticore.services.UserService;
import com.manticore.Manticore.services.implementations.UserServiceImplementation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true, securedEnabled = true)
public class SecurityConfig {

    @Bean
    public UserService userService() {
        return new UserServiceImplementation();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        AuthenticationManagerBuilder authenticationManagerBuilder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userService()).passwordEncoder(passwordEncoder());

        httpSecurity.authorizeRequests().antMatchers("resources/**").permitAll();

        httpSecurity.formLogin()
                    .loginPage("/sign-in").permitAll()
                    .loginProcessingUrl("/auth").permitAll()
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/", true)
                    .failureUrl("/sign-in?error")
                    .and()
                    .logout()
                    .logoutUrl("/sign-out")
                    .logoutSuccessUrl("/sign-in")
                    .and()
                    .csrf()
                    .disable();

        return httpSecurity.build();
    }
}