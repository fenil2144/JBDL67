package com.example.springSecurityDemo.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.springSecurityDemo.model.MyUser;
import com.example.springSecurityDemo.repository.MyUserRepository;

@Service
public class MyUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    MyUserRepository myUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return myUserRepository.findByEmail(username);
    }

    public MyUser save(MyUser myUser){
        return myUserRepository.save(myUser);
    }
}
