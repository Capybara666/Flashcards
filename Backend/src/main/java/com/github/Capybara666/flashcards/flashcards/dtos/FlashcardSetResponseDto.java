package com.github.Capybara666.flashcards.flashcards.dtos;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
public class FlashcardSetResponseDto {
    private long id;
    private String name;
    private List<FlashcardResponseDto> flashcards;
    private long userId;

}
