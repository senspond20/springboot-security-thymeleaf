package com.sensweb.demo.web.security.domain;

import java.util.Arrays;
import java.util.List;

import com.sensweb.demo.config.Authority;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
public class AccountRole{

    // 관리자 계정
    final String adminEmail = "senshig@naver.com";

    // 직원 계정
    final List<String> empEmailList = Arrays.asList(
        "ggg1@google.com",
        "ggg2@google.com",
        "ggg3@google.com" );

    private String email;
    private Authority authority;

    public AccountRole(String email){
        this.email = email;
        
        if(email.equals(adminEmail)){
            this.authority = Authority.ADMIN;
        }
        else if(empEmailList.contains(email)){
            this.authority = Authority.VIP;
        }
        else{
            this.authority = Authority.USER;
        }
    }
}
