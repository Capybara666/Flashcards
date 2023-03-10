package com.github.Capybara666.flashcards.flashcards;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FlashcardSetRepository extends JpaRepository<FlashcardSetEntity, Long> {

    @Query("SELECT fs FROM  FlashcardSetEntity fs WHERE fs.user.login = ?1")
    Optional<List<FlashcardSetEntity>> findAllByLogin(String login);

    @Query("SELECT fs FROM  FlashcardSetEntity fs WHERE fs.user.login = ?1 AND fs.name = ?2")
    Optional<FlashcardSetEntity> findOneByLoginAndName(String login, String name);

}
