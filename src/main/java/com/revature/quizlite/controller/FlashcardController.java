package com.revature.quizlite.controller;

import com.revature.quizlite.model.Flashcard;
import com.revature.quizlite.service.FlashcardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
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

    // Can anyone thing of a better way to pass parameters??? using the @RequestParam
    @GetMapping
    public List<Flashcard> findAllFlashcards(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Flashcard.Category category,
            @RequestParam(defaultValue = "false") boolean nullAnswer
            ){
        // i don't want to do dynamic query routing in my controller, that belongs in the service
        // call a routing method on the service and pass it the parameters -> that method will then decide what to call
        return flashcardService.findAllFlashcards(userId, category, nullAnswer);
    }

    @GetMapping("/{flashcardId}")
    public Flashcard findFlashcardById(@PathVariable Long flashcardId){
        return flashcardService.findFlashcardById(flashcardId);
    }
}
