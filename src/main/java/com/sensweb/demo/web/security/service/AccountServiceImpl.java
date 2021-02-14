package com.sensweb.demo.web.security.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.sensweb.demo.web.security.domain.Account;
import com.sensweb.demo.web.security.repository.AccountRepository;
import com.sensweb.demo.web.security.service.dto.AccountSaveRequestDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    /*
        
    */
    // @Transactional
	public Account save(AccountSaveRequestDto requestDto) {
        Account account = null;
        requestDto.setPassword(passwordEncoder.encode(requestDto.getPassword())); // 패스워드 암호화

        try{
            account = accountRepository.save(requestDto.toEntity());
        }catch(Exception e){
            logger.info("[Account Save] 이미 존재하는 email :  {}", requestDto.getEmail());
            logger.info("[Account Save] ===> return null");
        }
		return account;
	}

    /*
       모든 유저 조회
       Admin 권한이 없으면 불가능.
    */ 
    @Secured("ROLE_ADMIN")
    public List<Account> findByAll(){
        return accountRepository.findAll();
    }

    /*
       유저 권한 수정
       Admin 권한이 없으면 불가능.
    */ 

    @Secured("ROLE_ADMIN")
    public void update(){
       
    }

    /*
        UserDetail
    */ 
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmail(username);
        if(account == null) {
            throw new UsernameNotFoundException(username);
        }
        // account.setPassword(passwordEncoder.encode(account.getPassword())); 
        
        List<GrantedAuthority> authorities = new ArrayList<>();
        System.out.println("@@@@" + authorities);
        authorities.add(new SimpleGrantedAuthority(account.getAuthority()));
        
        System.out.println("@@@" + account.getPassword());

        // 아이디, 비밀번호, 권한 리스트 필요.
        return new User(account.getEmail(), account.getPassword(), authorities);
    }

    private boolean matches(CharSequence rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

  
    
}
