package com.lawencon.readcollection.config.security;

import com.lawencon.readcollection.business.user.service.UserService;
import com.lawencon.readcollection.config.security.filter.SecurityServletFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SecurityConfig {

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder,
                                                       UserService userService) throws Exception {

        return http
                .getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userService)
                .passwordEncoder(passwordEncoder).and().build();
    }

    @Bean
    public List<RequestMatcher> requestMatchers(){
        final List<RequestMatcher> matchers = new ArrayList<>();
        //matchers.add(new AntPathRequestMatcher("/users/**",HttpMethod.GET.name()));
        matchers.add(new AntPathRequestMatcher("/users/**",HttpMethod.POST.name()));
        matchers.add(new AntPathRequestMatcher("/login/**",HttpMethod.POST.name()));

        return matchers;
    }

    @Bean
    public WebSecurityCustomizer customizer() {
        return web -> requestMatchers().forEach((r)-> web.ignoring().requestMatchers(r));
    }

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity httpSecurity,final SecurityServletFilter securityServletFilter)
            throws Exception {
        httpSecurity.cors();
        httpSecurity.csrf().disable();
        httpSecurity.addFilterAt(securityServletFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
}
