package com.github.Capybara666.flashcards.users;

import com.github.Capybara666.flashcards.flashcards.FlashcardSetEntity;
import jakarta.persistence.*;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@ToString
@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private long id;

    private String login;

    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private Set<FlashcardSetEntity> flashcardSets = new HashSet<>();
}
