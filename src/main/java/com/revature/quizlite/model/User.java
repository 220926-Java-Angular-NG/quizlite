package com.revature.quizlite.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author bpinkerton
 *
 * User model for user information
 */

/**
 *      UserDetails expects
 *              username
 *              password
 *              authority
 *              isActive
 *              isPasswordExpired
 */
@Entity(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

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
    private String password;

    private Authority authority = Authority.USER;

    private Boolean isActive = true;

    public enum Authority{
        USER, ADMIN
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO: we want this to actually be a list of granted authorities
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

//    @OneToMany
//    @JsonIgnore // used to avoid circular dependency print
//    private List<Flashcard> flashcards;

}
