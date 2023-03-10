package com.github.Capybara666.flashcards.flashcards;

import com.github.Capybara666.flashcards.flashcards.dtos.FlashcardRequestedDto;
import com.github.Capybara666.flashcards.flashcards.dtos.FlashcardSetRequestedDto;
import com.github.Capybara666.flashcards.flashcards.dtos.FlashcardSetResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class FlashcardsController {

    private final FlashcardsService flashcardsService;

    @Autowired
    public FlashcardsController(FlashcardsService flashcardsService) {
        this.flashcardsService = flashcardsService;
    }

    @GetMapping(path = "/flashcards/{login}")
    public ResponseEntity<List<FlashcardSetResponseDto>> getFlashcardSets(@PathVariable("login") String login) {
        return flashcardsService.getFlashcardSets(login);
    }

    @GetMapping(path = "/flashcards/{login}/{flashcardSetName}")
    public ResponseEntity<FlashcardSetResponseDto> getFlashcardSet(@PathVariable("login") String login,
                                                                   @PathVariable("flashcardSetName") String flashcardSetName) {
        return this.flashcardsService.getFlashcardSet(login, flashcardSetName);
    }

    @DeleteMapping(path = "/flashcards/delete_set")
    public ResponseEntity<Void> deleteFlashcardSet(@RequestBody FlashcardSetRequestedDto flashcardSetRequestedDto) {
        return this.flashcardsService.deleteFlashcardSet(flashcardSetRequestedDto);
    }

    @DeleteMapping(path = "/flashcards/delete")
    public ResponseEntity<Void> deleteFlashcard(@RequestBody FlashcardRequestedDto flashcardRequestedDto) {
        return this.flashcardsService.deleteFlashcard(flashcardRequestedDto);
    }

    @PostMapping(path = "/flashcards/create_set")
    public ResponseEntity<Void> createFlashcardSet(@RequestBody FlashcardSetRequestedDto flashcardSetRequestedDto) {
        return this.flashcardsService.createFlashcardSet(flashcardSetRequestedDto);
    }

    @PostMapping(path = "/flashcards/create")
    public ResponseEntity<Void> createFlashcard(@RequestBody FlashcardRequestedDto flashcardRequestedDto) {
        return this.flashcardsService.createFlashcard(flashcardRequestedDto);
    }

    @PostMapping(path = "/flashcards/update_set")
    public ResponseEntity<Void> updateFlashcardSet(@RequestBody FlashcardSetRequestedDto flashcardSetRequestedDto) {
        return this.flashcardsService.updateFlashcardSet(flashcardSetRequestedDto);
    }

    @PostMapping(path = "/flashcards/update")
    public ResponseEntity<Void> updateFlashcard(@RequestBody FlashcardRequestedDto flashcardRequestedDto) {
        return this.flashcardsService.updateFlashcard(flashcardRequestedDto);
    }

}
