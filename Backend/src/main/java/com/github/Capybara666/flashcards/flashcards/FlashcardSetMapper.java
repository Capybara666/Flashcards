package com.github.Capybara666.flashcards.flashcards;

import com.github.Capybara666.flashcards.flashcards.dtos.FlashcardSetRequestedDto;
import com.github.Capybara666.flashcards.flashcards.dtos.FlashcardSetResponseDto;
import org.jetbrains.annotations.NotNull;

public class FlashcardSetMapper {

    public static FlashcardSetEntity requestedDtoToObject(@NotNull FlashcardSetRequestedDto flashcardSetRequestedDto) {
        return FlashcardSetEntity.builder()
                .id(flashcardSetRequestedDto.getId())
                .name(flashcardSetRequestedDto.getName())
                .build();
    }

    public static FlashcardSetResponseDto objectToResponseDto(@NotNull FlashcardSetEntity flashcardSetEntity) {
        return FlashcardSetResponseDto.builder()
                .id(flashcardSetEntity.getId())
                .name(flashcardSetEntity.getName())
                .flashcards(flashcardSetEntity.getFlashcards().stream()
                        .map(FlashcardMapper::objectToResponseDto)
                        .toList())
                .userId(flashcardSetEntity.getUser().getId())
                .build();
    }

}
