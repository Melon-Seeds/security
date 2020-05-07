package com.learn.c4_graphic_validation.component;

import org.springframework.security.authentication.AbstractUserDetailsReactiveAuthenticationManager;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class Test {

    Authentication authentication;
    AuthenticationProvider provider;

    ProviderManager manager;
    AbstractUserDetailsAuthenticationProvider authenticationProvider;
    DaoAuthenticationProvider daoAuthenticationProvider;

    UsernamePasswordAuthenticationFilter filter;
    AuthenticationDetailsSource source;

}
