package com.revature.quizlite.controller;

import com.revature.quizlite.model.Deck;
import com.revature.quizlite.model.Flashcard;
import com.revature.quizlite.service.DeckService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/decks")
@RequiredArgsConstructor
public class DeckController {

    private final DeckService deckService;

    @PostMapping
    public Deck createNewDeck(@RequestBody Deck deck){
        return deckService.createDeck(deck);
    }

    @GetMapping
    public List<Deck> findAllDecks(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false)Flashcard.Category category
            ){
        return deckService.findAllDecks(userId, category);
    }

    @GetMapping("/{deckId}")
    public Deck findDeckById(@PathVariable Long deckId){
        return deckService.findDeckById(deckId);
    }
}
