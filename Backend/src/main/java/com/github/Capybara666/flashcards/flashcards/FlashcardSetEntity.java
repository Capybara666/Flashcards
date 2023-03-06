package com.github.Capybara666.flashcards.flashcards;

import com.github.Capybara666.flashcards.users.UserEntity;
import jakarta.persistence.*;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import java.util.HashSet;
import java.util.Set;

@Entity
@Builder(toBuilder = true)
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "flashcard_sets")
public class FlashcardSetEntity {
    @Id
    @SequenceGenerator(name = "flashcard_set_sequence", sequenceName = "flashcard_set_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "flashcard_set_sequence")
    private long id;

    @Column(name = "name")
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "flashcardSet", orphanRemoval = true)
    private Set<FlashcardEntity> flashcards = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;
}
