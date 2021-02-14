package com.sensweb.demo.web.security.controller;

import javax.servlet.http.HttpServletRequest;

import com.sensweb.demo.web.security.domain.Account;
import com.sensweb.demo.web.security.ropository.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AccountController {

    // https://galid1.tistory.com/576
    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/create")
    @ResponseBody
    public Account createAccout() {
        Account account = new Account("senspond@gmail.com","1234","ROLE_USER");

        // {noop} : 패스워드 인코더를 사용하지 않음
        // Account account = new Account("senspond@gmail.com","{noop}1234","ROLE_USER");
        return accountRepository.save(account);
        // account.
    }

    @GetMapping(value = "/login")
    public String login(){
        return "login";
    }
    
    @PostMapping(value = "/login")
    public ModelAndView login(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid username and password!");
        }
    
        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }
    
        model.setViewName("/login");
        return model;
    }
}
