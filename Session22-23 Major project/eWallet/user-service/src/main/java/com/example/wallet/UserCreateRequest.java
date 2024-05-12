package com.example.wallet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreateRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private UserIdentifier userIdentifier;

    @NotBlank
    private String identifierValue;

    public User toUser(){
        return User.builder()
                .name(name)
                .password(password)
                .phoneNumber(phoneNumber)
                .email(email)
                .userIdentifier(userIdentifier)
                .identifierValue(identifierValue)
                .build();
    }



}
