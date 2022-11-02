package com.revature.quizlite.model;

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
 * A Deck will be a user created collection of flashcards
 * At a minimum a deck should have an author, a list of flashcards, and a label
 */
@Entity(name = "decks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Deck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deckId;

    @NotNull
    @Min(1)
    @Max(15)
    @Column(length = 15)
    private String label;

    @ManyToOne
    @NotNull
    private User curator;

    @ManyToMany
    @NotNull
    private List<Flashcard> flashcards;

    // as the ORM analyzes the annotations, it begins to create the tables
    // with many to many, it may create a redundant table unless you use the mappedBy property
}
