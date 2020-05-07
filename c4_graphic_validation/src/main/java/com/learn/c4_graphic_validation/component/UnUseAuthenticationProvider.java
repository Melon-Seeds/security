package com.learn.c4_graphic_validation.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;


public class UnUseAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
    UserDetailsService userDetailsService;
    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        //更多校验逻辑

        if(authentication.getCredentials()==null)
            throw new BadCredentialsException(this.messages.
                    getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials","密码不能为空"));
        else {
            String presentPassword = authentication.getCredentials().toString();
            if(!presentPassword.equals(userDetails.getPassword()))
                throw new BadCredentialsException(this.messages.
                        getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials","密码错误"));
        }
    }
    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        return userDetailsService.loadUserByUsername(username);
    }
}
