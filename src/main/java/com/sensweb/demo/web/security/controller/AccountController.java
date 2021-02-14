package com.sensweb.demo.web.security.controller;

import java.util.List;

import com.sensweb.demo.web.security.domain.Account;
import com.sensweb.demo.web.security.service.AccountService;
import com.sensweb.demo.web.security.service.dto.AccountSaveRequestDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    // https://galid1.tistory.com/576
    @Autowired
    private AccountService accountService;

    @GetMapping("/create")
    public ResponseEntity<?> createAccount() {
        return createAccout(AccountSaveRequestDto.builder().email("senshig@naver.com").password("1234").build());
    }

    @GetMapping("/update")
    public void update() {

    }

    @PostMapping("/signUp")
    @ResponseBody
    public ResponseEntity<?> createAccout(@RequestBody AccountSaveRequestDto requestDto) {
        System.out.println("@@#$#4 " + requestDto.getEmail());
        Account account = accountService.save(requestDto);
        if(account == null){
            return ResponseEntity.badRequest().body("msg : 이미 존재하는 이메일입니다. : " + requestDto.getEmail());
        }else{
            return ResponseEntity.ok().body(account);
        }    
      
        // account.
    }

    @GetMapping("/show")
    public List<Account> findAll(){
        return accountService.findByAll();
    }

}
