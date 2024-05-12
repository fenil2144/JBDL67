package com.example.wallet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class TransactionSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    TransactionService transactionService;

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(transactionService);
    }

    @Override
    protected void configure (HttpSecurity httpSecurity) throws Exception {
        httpSecurity.httpBasic().and().csrf().disable()
                .authorizeHttpRequests()
                .antMatchers("/transact/**").hasAuthority("usr")
                .and().formLogin();
    }

    @Bean
    PasswordEncoder getPassEncoder(){
        return new BCryptPasswordEncoder();
    }
}
