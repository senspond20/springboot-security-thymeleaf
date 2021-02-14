package com.sensweb.demo.config;

import com.sensweb.demo.web.security.controller.LoginSuccessHandler;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/*
1.securedEnabled

@Secured 애노테이션을 사용하여 인가 처리를 하고 싶을때 사용하는 옵션이다.특정 메서드 호출 이전에 권한을 확인한다.
기본값은 false
2.prePostEnabled

@PreAuthorize, @PostAuthorize 애노테이션을 사용하여 인가 처리를 하고 싶을때 사용하는 옵션이다.특정 메서드 호출 전, 후 이전에 권한을 확인한다.
기본값은 false : 

3.jsr250Enabled
@RolesAllowed 애노테이션을 사용하여 인가 처리를 하고 싶을때 사용하는 옵션이다.
기본값은 false
*/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = false, jsr250Enabled = false)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    

    // 정적 자원에 대해서는 Security 설정을 적용하지 않음.
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
     http
            .csrf().disable()   // 테스트를 위해 CSRF 비활성화
            .authorizeRequests()
                .antMatchers("/","/account/**","/login").permitAll() // /account/** , /login URL 요청에 대해서는 모든 접근 허용
                .antMatchers("/user/**").hasRole(Authority.USER.name()) // admin/**   URL 요청에 대해서는 ROLE_USER 권한을 가지고 있어야 함. 내부적으로 접두어 "ROLE_"가 붙는다.
                .antMatchers("/admin/**").hasRole(Authority.ADMIN.name())  // admin/** URL 요청에 대해서는 ROLE_ADMIN 권한을 가지고 있어야 함
                .anyRequest().authenticated()  // 나머지 모든 요청에 대해서는 로그인을 요구함 
                .and()
                // 폼 기반 로그인하는 경우에 대해 설정함
            .formLogin()
                 .loginPage("/login") // 로그인 페이지를 제공하는 URL을 설정함(없으면 기본 시큐리티에서 제공하는 login폼 사용)
              //.successForwardUrl("/home")   // 로그인 성공 URL을 설정함
                // .failureForwardUrl("/login/fail")     // 로그인 실패 URL을 설정함
                                         // login 페이지는 사용자가 접속가능해야하지~
                .loginProcessingUrl("/doLogin")
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(new LoginSuccessHandler())
                .and()
                .logout();
                // .logoutUrl("/doLogout")
                // .logoutSuccessUrl("/login");
            //     // .addFilterBefore(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    
}
