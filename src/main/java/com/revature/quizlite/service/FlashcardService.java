package com.revature.quizlite.service;

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

    public List<Flashcard> findAllFlashcards(){
        return flashcardRepository.findAll();
    }

    public List<Flashcard> findAllFlashcardsByUserId(Long userId){
        return flashcardRepository.findAllByAuthor_UserId(userId);
    }
}
