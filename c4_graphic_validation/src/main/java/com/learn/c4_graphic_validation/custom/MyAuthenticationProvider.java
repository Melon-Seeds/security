package com.learn.c4_graphic_validation.custom;

import com.learn.c4_graphic_validation.component.VerificationCodeException;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Component;

@Component("myAuthenticationProvider")
public class MyAuthenticationProvider extends DaoAuthenticationProvider {
    //构造器注入
    public MyAuthenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder){
        this.setUserDetailsService(userDetailsService);
        this.setPasswordEncoder(passwordEncoder);
    }
    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {
        //实现图形校验码的验证逻辑
        MyWebAuthenticationDetails details =(MyWebAuthenticationDetails)authentication.getDetails();
        if(!details.isImageCodeIsRight())
            throw new VerificationCodeException();

        //父类的验证逻辑
        super.additionalAuthenticationChecks(userDetails,authentication);
    }


}

