package com.revature.quizlite.controller;

// the goal of the ExceptionController is to listen for Exceptions that occur in our code
//  handle them correctly I.E. load the message into response body, but also change the status
// we are using AOP -> however we're relying on some abstractions

import com.revature.quizlite.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionController {

    @ExceptionHandler(ResourceNotFoundException.class)
    public String handleResourceNotFound(ResourceNotFoundException exception){
        log.info(exception.getMessage(), exception);
        return exception.getMessage();
    }
}
