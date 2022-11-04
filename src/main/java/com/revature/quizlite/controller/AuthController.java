package com.revature.quizlite.controller;

import com.revature.quizlite.model.DTO.AuthenticationRequest;
import com.revature.quizlite.model.DTO.AuthenticationResponse;
import com.revature.quizlite.model.DTO.RegistrationRequest;
import com.revature.quizlite.model.DTO.RegistrationResponse;
import com.revature.quizlite.service.JWTService;
import com.revature.quizlite.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public RegistrationResponse register(@RequestBody RegistrationRequest registrationRequest){
        return userService.registerUser(registrationRequest);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody AuthenticationRequest authenticationRequest){
        return userService.loginUser(authenticationRequest);
    }
}
