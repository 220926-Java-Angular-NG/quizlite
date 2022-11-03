package com.revature.quizlite.repository;

import com.revature.quizlite.model.Deck;
import com.revature.quizlite.model.Flashcard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeckRepository extends JpaRepository<Deck, Long> {
    // by default we can findAll, findByDeckId
    // what are some extended queries we may want to perform
    // findAllDecksByCurator
    // findAllDecksByFlashcardCategory

    List<Deck> findAllByCurator_UserId(Long userId);
    // TODO: Research spike -> figure this guy out
//    List<Deck> findAllByFlashcard_CategoryInFlashcards(Flashcard.Category category);
}
