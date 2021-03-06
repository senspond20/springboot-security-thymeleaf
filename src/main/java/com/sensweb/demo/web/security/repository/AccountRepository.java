package com.sensweb.demo.web.security.repository;

import java.util.List;

import com.sensweb.demo.web.security.domain.Account;
import com.sensweb.demo.web.security.service.dto.AccountSaveRequestDto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{

    public Account save(AccountSaveRequestDto requestDto);
    
    public List<Account> findAll();

    public Account findByEmail(String username);

    public List<Account> findByAuthority(String authority);
}
