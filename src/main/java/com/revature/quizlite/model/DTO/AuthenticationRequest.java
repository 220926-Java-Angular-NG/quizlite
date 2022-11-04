package com.revature.quizlite.model.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequest {
    // what do we need from a user to authenticate them
    private String username;
    private String password;
}
