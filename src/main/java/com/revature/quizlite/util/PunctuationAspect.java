package com.revature.quizlite.util;

import com.revature.quizlite.model.Flashcard;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

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
@Component
@Slf4j
public class PunctuationAspect {

    // we can declare advice as methods

    @Before("execution(* com.revature.quizlite.service.FlashcardService.createFlashcard(..)) && args(flashcard)")
    public void punctuate(Flashcard flashcard) throws Throwable {
        flashcard.setQuestion(checkQuestion(flashcard.getQuestion()));
        flashcard.setAnswer(checkAnswer(flashcard.getAnswer()));
    }

    private String checkQuestion(String question){
        return question.charAt(question.length()-1) != '?' ? question + "?" : question;
    }

    private String checkAnswer(String answer){
        return answer.charAt(answer.length()-1) != '.' ? answer + "." : answer;
    }
}
