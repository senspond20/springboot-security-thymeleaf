package com.sensweb.demo.web.security.service.dto;

import com.sensweb.demo.web.security.domain.Account;
import com.sensweb.demo.web.security.domain.AccountRole;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AccountSaveRequestDto {

    private String email;
    private String password;

    @Builder
    public AccountSaveRequestDto(String email, String password){
        super();
        this.email = email;
        this.password = password;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public Account toEntity(){
        return Account.builder()
                      .email(email)
                      .password(password)
                      .authority(new AccountRole(email).getAuthority())
                      .build();
    }
}
