package com.learn.c4_graphic_validation.component;

import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class VerificationCodeFilter extends OncePerRequestFilter {

    private final AuthenticationFailureHandler authenticationFailureHandler = (httpServletRequest, httpServletResponse, e)->{
        PrintWriter writer = httpServletResponse.getWriter();
        writer.write(e.getMessage()+e.toString());
        writer.write("error code");
    };

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        if(!"/auth/form".equals(httpServletRequest.getRequestURI()))
            filterChain.doFilter(httpServletRequest,httpServletResponse);
        else {
            try {
                verificationCode(httpServletRequest);
                filterChain.doFilter(httpServletRequest,httpServletResponse);
            }catch (VerificationCodeException e){
                authenticationFailureHandler.onAuthenticationFailure(httpServletRequest,httpServletResponse,e);
            }
        }
    }
    public void verificationCode(HttpServletRequest request) throws VerificationCodeException{
        String requestCode = request.getParameter("captcha");
        HttpSession session = request.getSession();
        String savedCode = (String)session.getAttribute("captcha");
        if(!StringUtils.isEmpty(savedCode))
            session.removeAttribute("captcha");
        if(StringUtils.isEmpty(requestCode)||StringUtils.isEmpty(savedCode)||!requestCode.equals(savedCode))
            throw new VerificationCodeException();
    }

}
