package com.example.wallet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.example.wallet.UserConstants.ADMIN_AUTHORITY;
import static com.example.wallet.UserConstants.SERVICE_AUTHORITY;
import static com.example.wallet.UserConstants.USER_AUTHORITY;

@Configuration
public class UserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
                .and().csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/user/**").permitAll()
                .antMatchers("/user/**").hasAuthority(USER_AUTHORITY)
                .antMatchers("/admin/**").hasAnyAuthority(ADMIN_AUTHORITY, SERVICE_AUTHORITY)
                .and().formLogin();
    }
}