package com.sensweb.demo.web.security.controller;

import com.sensweb.demo.web.security.domain.Account;
import com.sensweb.demo.web.security.ropository.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;
    @GetMapping("/create")
    public Account createAccout() {
        Account account = new Account("senspond@gmail.com","{noop}1234","ROLE_USER");
        return accountRepository.save(account);
        // account.
    }
}
