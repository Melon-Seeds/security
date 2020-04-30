package com.learn.c2_form_auth.Component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class Beans {
    @Bean("inMemoryUserDetailsService")
    public UserDetailsService inMemoryUserDetailsService(){
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("user").password("user").roles("USER").build());
        manager.createUser(User.withUsername("admin").password("admin").roles("ADMIN").build());
        return manager;
    }

    @Autowired
    private DataSource dataSource;
    @Bean("jdbcUserDetailsManager")
    public UserDetailsService userDetailsService(){
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager();
        manager.setDataSource(dataSource);
        if(!manager.userExists("user"))
            manager.createUser(User.withUsername("user").password("user").roles("USER").build());
        if(!manager.userExists("admin"))
            manager.createUser(User.withUsername("admin").password("admin").roles("ADMIN").build());
        return manager;
    }

}
