package com.example.springSecurityDemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService);

//        auth.inMemoryAuthentication()
//                .withUser("john").password("john123")
//                .authorities("dev").and()
//                .withUser("preet").password("preet123")
//                .authorities("qa");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and()
                .csrf().disable()
                .authorizeHttpRequests()
                .antMatchers("/developercode/**").hasAuthority("dev")
                .antMatchers("/qacode/**").hasAuthority("qa")
                .antMatchers("/technicalcode/**").hasAnyAuthority("dev","qa")
                .antMatchers("/home","/signup","/register").permitAll()
                .anyRequest().authenticated()
                .and().formLogin();
    }

//    1) Role based Authority: Ant matchers will have multiple roles for a set of apis and
//                             user in db will have single role.
//    2) Action based Authority: Ant matcher will always have single role for a set of apis and
//                               user in database will have multiple actions in authority column
//    3) combination of Role and Action based

    @Bean
    PasswordEncoder getPE(){
        return new BCryptPasswordEncoder();
    }

}
