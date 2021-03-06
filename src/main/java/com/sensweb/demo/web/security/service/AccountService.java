package com.sensweb.demo.web.security.service;

import java.util.List;

import com.sensweb.demo.web.security.domain.Account;
import com.sensweb.demo.web.security.service.dto.AccountSaveRequestDto;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface AccountService extends UserDetailsService {
    
    public Account save(AccountSaveRequestDto requestDto);

    public List<Account> findAll();

    public Account findByEmail(String email);

    @Secured("ROLE_ADMIN")
    public void update();
}
