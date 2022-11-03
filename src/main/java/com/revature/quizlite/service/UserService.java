package com.revature.quizlite.service;

import com.revature.quizlite.ResourceNotFoundException;
import com.revature.quizlite.model.User;
import com.revature.quizlite.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User createUser(User user){
        return userRepository.save(user);
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
}
