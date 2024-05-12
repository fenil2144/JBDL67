package com.example.wallet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/user")
    public ResponseEntity createUser(@RequestBody UserCreateRequest userCreateRequest) throws JsonProcessingException {
        userService.create(userCreateRequest);
        return new ResponseEntity("User Created Successfully", HttpStatus.CREATED);
    }

    @GetMapping("/user")
    public User getUserDetails(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return userService.loadUserByUsername(user.getUsername());
    }

    @GetMapping("/admin/all/users")
    public List<User> getAllUserDetails(){
        return userService.getAllUserDetails();
    }

    @GetMapping("/admin/all/{userId}")
    public User getAllUserDetails(@PathVariable("userId") String userId){
        return userService.loadUserByUsername(userId);
    }

}
