package com.github.Capybara666.flashcards.flashcards;

import com.github.Capybara666.flashcards.users.UserEntity;
import jakarta.persistence.*;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

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
    @OneToMany(mappedBy = "flashcardSet", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<FlashcardEntity> flashcards = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

}
