package com.revature.quizlite.service;

import com.revature.quizlite.model.Deck;
import com.revature.quizlite.model.Flashcard;
import com.revature.quizlite.repository.DeckRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeckService {

    private final DeckRepository deckRepository;

    public Deck createDeck(Deck deck){
        return deckRepository.save(deck);
    }

    // TODO: add flashcards to existing deck

    public List<Deck> findAllDecks(Long userId, Flashcard.Category category){
        if(userId != null) return findAllDecksByUserId(userId);

        return findAllDecks();
    }

    public Deck findDeckById(Long deckId){
        return deckRepository.findById(deckId)
                .orElseThrow(() -> new RuntimeException("Deck not found with id: " + deckId));
    }

    private List<Deck> findAllDecks(){
        return deckRepository.findAll();
    }

    private List<Deck> findAllDecksByUserId(Long userId){
        return deckRepository.findAllByCurator_UserId(userId);
    }
}
