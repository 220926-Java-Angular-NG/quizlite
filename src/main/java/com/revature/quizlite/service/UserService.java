package com.revature.quizlite.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.quizlite.ResourceNotFoundException;
import com.revature.quizlite.model.DTO.AuthenticationRequest;
import com.revature.quizlite.model.DTO.AuthenticationResponse;
import com.revature.quizlite.model.DTO.RegistrationRequest;
import com.revature.quizlite.model.DTO.RegistrationResponse;
import com.revature.quizlite.model.User;
import com.revature.quizlite.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;
    private final JWTService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public User createUser(User user){
        return userRepository.save(user);
    }

    public RegistrationResponse registerUser(RegistrationRequest registrationRequest){
        // a password is stored in plain text
        String password = registrationRequest.getPassword();
        password = passwordEncoder.encode(password);
        registrationRequest.setPassword(password);

       return (RegistrationResponse) generateResponse(
               createUser(
                       objectMapper.convertValue(registrationRequest, User.class)));
    }

    public AuthenticationResponse loginUser(AuthenticationRequest authenticationRequest) {
        // try to authenticate the user
        // short circuit the execution with an exception
        // TODO: confirm the password being checked against the database is encoded first
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(), authenticationRequest.getPassword())
        );

        // what we need to do now is get the user loaded, then generate the token/response
        return generateResponse(findUserByUsername(authenticationRequest.getUsername()));
    }

    private AuthenticationResponse generateResponse(User user){
        return new AuthenticationResponse(jwtService.generateToken(user));
    }

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    public User findUserById(Long userId){
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
    }

    public User findUserByUsername(String username){
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + username));
    }

    public boolean deleteUserById(Long userId){
        // deleting the user
        userRepository.deleteById(userId);

        // making sure it doesn't exist
        return !userRepository.findById(userId).isPresent();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findUserByUsername(username);
    }


}
