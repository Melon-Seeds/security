package com.learn.c4_graphic_validation.custom;

import com.learn.c4_graphic_validation.component.VerificationCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@EnableWebSecurity
public class CustomWebSecurityConfig  extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> myAuthenticationDetailsSource;

    @Resource(name = "myAuthenticationProvider")
    private AuthenticationProvider provider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(provider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers("/app/api/**","/captcha.jpg").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .authenticationDetailsSource(myAuthenticationDetailsSource)
                .loginPage("/myLogin.html")
                .loginProcessingUrl("/auth/form").permitAll()
                .and()
                .csrf().disable();
        //将过滤器添加在UsernamePasswordAuthenticationFilter.class之前
        http.addFilterBefore(new VerificationCodeFilter(), UsernamePasswordAuthenticationFilter.class);
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**","/img/**");
    }
}
