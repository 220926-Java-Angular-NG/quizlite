package com.revature.quizlite.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author bpinkerton
 * Flashcard models flashcard information
 * flashcards should have at the minimum an author(user), a question, an answer, and category
 */
@Entity(name = "flashcards")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Flashcard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flashcardId;

    @ManyToOne
//    @NotNull
    private User author;

//    @NotNull
//    @Min(1)
//    @Max(255)
    private String question;

//    @NotNull
//    @Min(1)
//    @Max(255)
    private String answer;
//
//    @NotNull
    @Enumerated
    private Category category;
    public enum Category{
        SPACE,
        BUGS,
        FRENCH_DISHES,
        MUSIC_HISTORY,
        BANK,
        BANKSY,
        KELPO,
        ENNUI,
        MMA,
        MISK
    }

    @ManyToMany(mappedBy = "flashcards")
    @JsonIgnore
    private List<Deck> decks;
}
