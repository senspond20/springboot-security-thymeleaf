package com.sensweb.demo.web.security.ropository;

import java.util.List;

import com.sensweb.demo.web.security.domain.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{

    public Account findByEmail(String username);

    public List<Account> findByAuthority(String authority);
}
