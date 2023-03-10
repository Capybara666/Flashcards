package com.github.Capybara666.flashcards.flashcards;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder(toBuilder = true)
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "flashcards")
public class FlashcardEntity {
    @Id
    @SequenceGenerator(name = "flashcard_sequence", sequenceName = "flashcard_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "flashcard_sequence")
    private long id;

    @Column(name = "front_content")
    private String frontContent;

    @Column(name = "back_content")
    private String backContent;

    @ManyToOne
    @JoinColumn(name = "flashcard_set_id", referencedColumnName = "id")
    private FlashcardSetEntity flashcardSet;

}
