package com.sensweb.demo.web.security.controller;

import com.sensweb.demo.web.security.domain.Account;
import com.sensweb.demo.web.security.ropository.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    // https://galid1.tistory.com/576
    @Autowired
    private AccountRepository accountRepository;
    @GetMapping("/create")
    public Account createAccout() {
        Account account = new Account("senspond@gmail.com","1234","ROLE_USER");

        // {noop} : 패스워드 인코더를 사용하지 않음
        // Account account = new Account("senspond@gmail.com","{noop}1234","ROLE_USER");
        return accountRepository.save(account);
        // account.
    }
}
