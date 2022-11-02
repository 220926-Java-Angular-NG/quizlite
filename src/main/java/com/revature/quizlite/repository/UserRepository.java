package com.revature.quizlite.repository;

import com.revature.quizlite.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    // Optional<User> findByUsernameIsContainingIgnoreCase(String word);
    // Hibernate is able to see fields on the class
    // and generate SQL queries based on keywords/fields
    //  it can generate something like -> select * from users where username = ?
    //                                  -> select * from users where username like ?
    //                                   -> stmt.setString(word.toLower())

}
