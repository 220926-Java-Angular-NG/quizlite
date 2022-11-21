package com.revature.quizlite.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.quizlite.model.DTO.AuthenticationRequest;
import com.revature.quizlite.model.DTO.AuthenticationResponse;
import com.revature.quizlite.model.DTO.RegistrationRequest;
import com.revature.quizlite.model.DTO.RegistrationResponse;
import com.revature.quizlite.model.User;
import com.revature.quizlite.repository.UserRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

@SpringBootTest
//@TestPropertySource("classpath:test-application.properties") DON'T USE THIS HERE!!!!
public class UserServiceTests {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private ObjectMapper objectMapper;
    @Mock
    private JWTService jwtService;
    @Mock
    private AuthenticationManager authenticationManager;

    private User newUser;
    private User dbUser;

    private RegistrationRequest registrationRequest;
    private RegistrationResponse registrationResponse;
    private AuthenticationRequest authenticationRequest;
    private AuthenticationResponse authenticationResponse;
    private final String token = "testToken";

    private final String username = "testUsername";


    @BeforeEach
    public void populateObjects(){
        newUser = new User();
        newUser.setUsername("testUsername");
        newUser.setPassword("testPassword");
        newUser.setAuthority(User.Authority.USER);
        newUser.setIsActive(true);


        dbUser = new User();
        dbUser.setUserId(2L);
        dbUser.setUsername("testUsername");
        dbUser.setPassword("encryptedPassword");
        dbUser.setAuthority(User.Authority.USER);
        dbUser.setIsActive(true);

        registrationRequest = new RegistrationRequest();
        registrationRequest.setUsername("testUsername");
        registrationRequest.setPassword("testPassword");

        registrationResponse = new RegistrationResponse(token);

        authenticationRequest = new AuthenticationRequest();
        authenticationRequest.setUsername("testUsername");
        authenticationRequest.setPassword("testPassword");

        authenticationResponse = new AuthenticationResponse(token);
    }

    @BeforeEach
    public void setUpStubbing(){

        Mockito.when(userRepository.save(newUser)).thenReturn(dbUser);
    }

    @Test
    public void givenNewUser_createUser_returnsCreatedEntity(){
        // define your stubbing behavior
        Mockito.when(passwordEncoder.encode("testPassword")).thenReturn("encryptedPassword");
//        Mockito.when(userRepository.save(newUser)).thenReturn(dbUser);
        // provide your given arguments -> taken care of in BeforeEach
        // call your method to be tested
        User user = userService.createUser(newUser);

        // make some assertions
        Assertions.assertEquals(dbUser.getUserId(), user.getUserId());
        Assertions.assertEquals(dbUser.getUsername(), user.getUsername());
        Assertions.assertEquals(dbUser.getPassword(), user.getPassword());
        Assertions.assertEquals(dbUser.getAuthority(), user.getAuthority());
        Assertions.assertEquals(dbUser.getIsActive(), user.getIsActive());
    }

    @Test
    public void givenRegistrationRequest_registerUser_returnsRegistrationResponse(){
        Mockito.when(objectMapper.convertValue(registrationRequest, User.class)).thenReturn(newUser);
//        Mockito.when(userRepository.save(newUser)).thenReturn(dbUser);
        Mockito.when(jwtService.generateToken(dbUser)).thenReturn(token);

        RegistrationResponse response = userService.registerUser(registrationRequest);
        Assertions.assertEquals(registrationResponse.getToken(), response.getToken());
    }

    @Test
    public void givenAuthenticationRequest_loginUser_returnsAuthenticationResponse(){
        Mockito.when(userRepository.findByUsername(username)).thenReturn(Optional.of(dbUser));
        Mockito.when(jwtService.generateToken(dbUser)).thenReturn(token);

        AuthenticationResponse response = userService.loginUser(authenticationRequest);
        Assertions.assertEquals(authenticationResponse.getToken(), response.getToken());
    }
}
