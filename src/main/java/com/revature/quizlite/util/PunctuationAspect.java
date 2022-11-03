package com.revature.quizlite.util;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author bpinkerton
 * The goal of the class is to intercept the create method on the FlashcardService
 * and ensure they are properly punctuated
 * Questions end in a question mark
 * Answers end in a period
 */

/*
    Aspect Oriented Programming

    Aspect          a collection of advice
    Advice          is some sort of interception/manipulation on behalf of your program's execution
                        Before              intercept before the point in time(pointcut) you have declared
                        After Returning     intercept after your pointcut executes correctly
                        After Throwing      intercept after your pointcut throws an exception
                        After Finally       intercept after your pointcut regardless of success
                        Around              before and after your pointcut
    Pointcut        a point in time on which we want to listen
                        point these at any execution within
                            packages
                            classes
                            method
 */

@Aspect
@Slf4j
public class PunctuationAspect {

    // we can declare advice as methods

    @Around("execution(com...FlashcardService.createFlashcard(*))")
    public Object punctuate(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("We made it!!");

        return proceedingJoinPoint.proceed();
    }
}
