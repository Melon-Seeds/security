package com.learn.c4_graphic_validation.controller;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/aa/")
@RestController
public class AuthController {

    @PostMapping("aa")
    public String form(HttpServletRequest request){
        System.out.println("form");
        return request.toString();
    }


}
