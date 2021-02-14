package com.sensweb.demo.web.security.service;

import com.sensweb.demo.web.security.domain.Account;
import com.sensweb.demo.web.security.service.dto.AccountSaveRequestDto;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface AccountService2 extends UserDetailsService {
    
    public Account save(AccountSaveRequestDto requestDto);

}
