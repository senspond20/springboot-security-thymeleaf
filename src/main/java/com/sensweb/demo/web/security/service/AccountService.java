package com.sensweb.demo.web.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.sensweb.demo.web.security.domain.Account;
import com.sensweb.demo.web.security.ropository.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    // UserDetail

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmail(username);
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(account.getAutority()));

        // 아이디, 비밀번호, 권한 리스트 필요.
        return new User(account.getEmail(), account.getPassword(), authorities);
    }
    
}
