package com.example.minorproject.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.minorproject.models.MyUser;
import com.example.minorproject.repository.MyUserRepositoryInterf;
import com.example.minorproject.repositoryImpl.MyUserCacheRepository;
import com.example.minorproject.requests.UserCreateRequest;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Value("${users.authority.student}")
    String studentAuthority;

    @Value("${users.authority.admin}")
    String adminAuthority;

    @Autowired
    MyUserRepositoryInterf myUserRepositoryInterf;

    @Autowired
    MyUserCacheRepository myUserCacheRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser myUser = myUserCacheRepository.get(username);

        // fetch from database if its not present in cache
        if(myUser == null){
            myUser = myUserRepositoryInterf.findByUsername(username);
            if(myUser != null){
                myUserCacheRepository.set(myUser);
            }
        }
        return myUser;
    }

    public MyUser createUser(UserCreateRequest userCreateRequest){

        MyUser.MyUserBuilder myUserBuilder = MyUser.builder()
                .username(userCreateRequest.getUsername())
                .password(passwordEncoder.encode(userCreateRequest.getPassword()));

        if(userCreateRequest.getStudent() != null){
            myUserBuilder.authority(studentAuthority);
        } else {
            myUserBuilder.authority(adminAuthority);
        }
        return myUserRepositoryInterf.save(myUserBuilder.build());
    }
}
