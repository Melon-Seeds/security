package com.learn.c4_graphic_validation.custom;

import lombok.Getter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class MyWebAuthenticationDetails extends WebAuthenticationDetails {

    @Getter
    private boolean imageCodeIsRight;

    String imageCode;
    String savedImageCode;

    public MyWebAuthenticationDetails(HttpServletRequest request){
        super(request);
        this.imageCode = request.getParameter("captcha");
        HttpSession session = request.getSession();
        this.savedImageCode=(String) session.getAttribute("captcha");

        if(!StringUtils.isEmpty(imageCode)){
            session.removeAttribute("captcha");
            if(!imageCode.equals(savedImageCode))
                imageCodeIsRight=true;
        }
    }

}
