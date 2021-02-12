package com.sensweb.demo.web.security.ropository;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.sensweb.demo.web.security.domain.Account;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepository {
    
    private Map<String,Object> accounts = new HashMap<>();

    public Account save(Account account){
        Random random = new Random();
        account.setId(random.nextLong());
        accounts.put(account.getEmail(), account);
        return account;
    }
    public Account findByEmail(String username){
        return (Account) accounts.get(username);
    }
    
 
}
