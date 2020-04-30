package com.learn.c2_form_auth.service;

import com.learn.c2_form_auth.dao.UserMapper;
import com.learn.c2_form_auth.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("myUserDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findByUserName(username);
        if(user==null)
            throw new UsernameNotFoundException("用户不存在");
        //security提供的工具类，该方法用于将逗号隔开的权限字符串装换为权限列表--这个也可以自己写
        user.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRoles()));
        return user;
    }
    private List<GrantedAuthority> generateAuthorities(String roles){
        List<GrantedAuthority> authorities = new ArrayList<>();
        if(roles!=null&&roles.length()>0)
            for(String role : roles.split(","))
                authorities.add(new SimpleGrantedAuthority(role));
        return authorities;
    }
}
