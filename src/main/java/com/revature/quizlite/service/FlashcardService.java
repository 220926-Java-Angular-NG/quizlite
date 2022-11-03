package com.revature.quizlite.service;

import com.revature.quizlite.ResourceNotFoundException;
import com.revature.quizlite.model.Flashcard;
import com.revature.quizlite.repository.FlashcardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FlashcardService {

    private final FlashcardRepository flashcardRepository;

    public Flashcard createFlashcard(Flashcard flashcard){
        return flashcardRepository.save(flashcard);
    }

    public List<Flashcard> findAllFlashcards(Long userId, Flashcard.Category category, boolean nullAnswer){
        // determine which parameters exist and route to another service method
        if(nullAnswer) return findAllFlashcardsWhereAnswerIsNull();

        if(userId != null && category != null) return findAllFlashcardsByUserIdAndCategory(userId, category);

        if(userId != null) return findAllFlashcardsByUserId(userId);

        if(category != null) return findAllFlashcardsByCategory(category);

        return findAllFlashcards();
    }

    /**
     *      Helper (private) methods are leveraged by other methods in the class to get the job
     *      as opposed to Accessor (public) methods which are our way into the class to invoke functionality
     */

    private List<Flashcard> findAllFlashcards(){
        return flashcardRepository.findAll();
    }

    private List<Flashcard> findAllFlashcardsByUserId(Long userId){
        return flashcardRepository.findAllByAuthor_UserId(userId);
    }

    private List<Flashcard> findAllFlashcardsWhereAnswerIsNull(){
        return flashcardRepository.findAllByAnswerIsNull();
    }

    private List<Flashcard> findAllFlashcardsByUserIdAndCategory(Long userId, Flashcard.Category category){
        return flashcardRepository.findAllByAuthor_UserIdAndCategory(userId, category);
    }

    private List<Flashcard> findAllFlashcardsByCategory(Flashcard.Category category){
        return flashcardRepository.findAllByCategory(category);
    }

    public Flashcard findFlashcardById(Long flashcardId) {
        return flashcardRepository.findById(flashcardId)
                .orElseThrow(() -> new ResourceNotFoundException("Flashcard not found with id: " + flashcardId));
    }
}
