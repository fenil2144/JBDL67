package com.example.minorproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Value("${users.authority.student}")
    String studentAuthority;

    @Value("${users.authority.admin}")
    String adminAuthority;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and()
                .csrf().disable()
                .authorizeHttpRequests()
                .antMatchers("/student/**").hasAuthority(studentAuthority)
                .antMatchers("/admin/**").hasAuthority(adminAuthority)
                .antMatchers("/student_for_admin/**").hasAuthority(adminAuthority)
                .antMatchers("/books/**").hasAnyAuthority(studentAuthority, adminAuthority)
                .antMatchers("/saveBook/**").hasAuthority(adminAuthority)
                .antMatchers("/register").permitAll()
                .anyRequest().authenticated()
                .and().formLogin();
    }

//    1) Role based Authority: Ant matchers will have multiple roles for a set of apis and
//                             user in db will have single role.
//    2) Action based Authority: Ant matcher will always have single role for a set of apis and
//                               user in database will have multiple actions in authority column
//    3) combination of Role and Action based

}

