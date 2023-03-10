package com.github.Capybara666.flashcards.flashcards.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class FlashcardResponseDto {
    private long id;
    private String frontContent;
    private String backContent;
    private long setId;

}
