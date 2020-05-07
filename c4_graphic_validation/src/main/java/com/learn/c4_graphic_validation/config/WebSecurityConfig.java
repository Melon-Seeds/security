package com.learn.c4_graphic_validation.config;

import com.learn.c4_graphic_validation.component.VerificationCodeFilter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


//@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    //自定义登录界面方法
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
            .antMatchers("/app/api/**","/captcha.jpg").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin().loginPage("/myLogin.html")
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
