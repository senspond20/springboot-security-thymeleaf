package com.sensweb.demo.web.security.domain;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.sensweb.demo.config.Authority;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.util.Assert;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@ToString
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="ID", columnDefinition = "INT(11)")
    private Long id;

    @Column(name ="EMAIL", unique = true, nullable = false)
    private String email;

    @Column(name ="PASSWORD", length = 120, nullable = false)
    private String password;

    @Column(name ="ROLE", length = 15, nullable = false)
    private String authority;

    @Column(name ="JOIN_DATE", nullable = false)
    private LocalDate  joinDate;

    @Builder
    public Account(String email, String password, Authority authority) {
        Assert.hasText(email,    "@@@@@@@ : email must not be empty");
        Assert.hasText(password, "@@@@@@@ : password must not be empty");
        Assert.hasText(authority.name(), "@@@@@@@ : authority must not be empty");

        this.email = email;
        this.password = password;
        this.authority = authority.name();
        this.joinDate = LocalDate.now();
    }
}