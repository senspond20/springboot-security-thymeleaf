package com.sensweb.demo.web.security.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    
    @PostMapping("/auth")
    public void auto(HttpServletRequest request){
        System.out.println(request.getParameter("password"));
    }
}
