package com.revature.quizlite.controller;

import com.revature.quizlite.model.User;
import com.revature.quizlite.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // what does @RestController do differently??? -> Automatically adds @ResponseBody to methods
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @GetMapping
    public List<User> findAllUsers(){
        return userService.findAllUsers();
    }

    // GET localhost:8080/users/1 -> getByUserId
    @GetMapping("/{userId}")
    public User getUserById(@PathVariable Long userId){
        return userService.findUserById(userId);
    }
}
