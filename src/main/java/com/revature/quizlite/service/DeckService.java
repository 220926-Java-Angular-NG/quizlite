package com.revature.quizlite.service;

import com.revature.quizlite.model.Deck;
import com.revature.quizlite.model.Flashcard;
import com.revature.quizlite.repository.DeckRepository;
import com.revature.quizlite.repository.FlashcardRepository;
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
        if(userId != null && category != null) return findAllDecksByUserIdContainingCategory(userId, category);
        if(userId != null) return findAllDecksByUserId(userId);
        if(category != null) return findAllDecksContainingCategory(category);

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

    private List<Deck> findAllDecksContainingCategory(Flashcard.Category category){
        return deckRepository.findAllByFlashcardsCategory(category);
    }

    private List<Deck> findAllDecksByUserIdContainingCategory(Long userId, Flashcard.Category category){
        return deckRepository.findAllByCurator_UserIdAndFlashcardsCategory(userId, category);
    }
}
