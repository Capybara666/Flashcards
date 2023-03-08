package com.github.Capybara666.flashcards.flashcards;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class FlashcardsController {

    private final FlashcardsService flashcardsService;

    @Autowired
    public FlashcardsController(FlashcardsService flashcardsService) {
        this.flashcardsService = flashcardsService;
    }

}
