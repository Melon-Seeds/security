package com.learn.c2_form_auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.learn.c2_form_auth.dao")
public class C2FormAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(C2FormAuthApplication.class, args);
    }

}
