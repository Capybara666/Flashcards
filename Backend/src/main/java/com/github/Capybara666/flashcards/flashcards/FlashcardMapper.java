package com.github.Capybara666.flashcards.flashcards;

import com.github.Capybara666.flashcards.flashcards.dtos.FlashcardRequestedDto;
import com.github.Capybara666.flashcards.flashcards.dtos.FlashcardResponseDto;
import org.jetbrains.annotations.NotNull;

public class FlashcardMapper {

    public static FlashcardEntity requestedDtoToObject(@NotNull FlashcardRequestedDto flashcardRequestedDto) {
        return FlashcardEntity.builder()
                .id(flashcardRequestedDto.getId())
                .frontContent(flashcardRequestedDto.getFrontContent())
                .backContent(flashcardRequestedDto.getBackContent())
                .build();
    }

    public static FlashcardResponseDto objectToResponseDto(@NotNull FlashcardEntity flashcardEntity) {
        return FlashcardResponseDto.builder()
                .id(flashcardEntity.getId())
                .frontContent(flashcardEntity.getFrontContent())
                .backContent(flashcardEntity.getBackContent())
                .setId(flashcardEntity.getFlashcardSet().getId())
                .build();
    }

}
