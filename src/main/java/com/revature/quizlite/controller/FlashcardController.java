package com.revature.quizlite.controller;

import com.revature.quizlite.model.Flashcard;
import com.revature.quizlite.service.FlashcardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flashcards")
@RequiredArgsConstructor
public class FlashcardController {

    private final FlashcardService flashcardService;

    @PostMapping
    public Flashcard createFlashcard(@RequestBody Flashcard flashcard){
        return flashcardService.createFlashcard(flashcard);
    }

    @GetMapping
    public List<Flashcard> findAllFlashcards(){
        return flashcardService.findAllFlashcards();
    }

    @GetMapping("/user/{userId}") // /flashcards/user/id
    public List<Flashcard> findAllFlashcardsByUserId(@PathVariable Long userId){
        return flashcardService.findAllFlashcardsByUserId(userId);
    }
}
