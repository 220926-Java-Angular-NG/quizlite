package com.revature.quizlite.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

/**
 * @author bpinkerton
 *
 * User model for user information
 */
@Entity(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

//    @Min(5)
//    @Max(20)
//    @NotNull
    @Column(unique = true, length = 20)
    private String username;

//    @Min(8)
//    @Max(24)
//    @NotNull
    @Column(length = 24)
    private String password;

//    @OneToMany
//    @JsonIgnore // used to avoid circular dependency print
//    private List<Flashcard> flashcards;
}
