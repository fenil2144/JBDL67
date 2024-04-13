package com.example.springSecurityDemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("john").password("john123")
                .authorities("dev").and()
                .withUser("preet").password("preet123")
                .authorities("qa");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .antMatchers("/developercode/**").hasAuthority("dev")
                .antMatchers("/qacode/**").hasAuthority("qa")
                .antMatchers("/technicalcode/**").hasAnyAuthority("dev","qa")
                .antMatchers("/home","/signup","/register").permitAll()
                .anyRequest().authenticated()
                .and().formLogin();
    }

    @Bean
    PasswordEncoder getPE(){
        return NoOpPasswordEncoder.getInstance();
    }

}
