package com.revature.quizlite.repository;

import com.revature.quizlite.model.Flashcard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlashcardRepository extends JpaRepository<Flashcard, Long> {

    List<Flashcard> findAllByAuthor_UserId(Long userId);
    // what other criteria might we want to filter flashcards down by? category?? category AND author ?? where answer is null

    List<Flashcard> findAllByCategory(Flashcard.Category category);
    List<Flashcard> findAllByAuthor_UserIdAndCategory(Long userId, Flashcard.Category category);
    List<Flashcard> findAllByAnswerIsNull();
}
