package com.github.Capybara666.flashcards.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT u FROM  UserEntity u WHERE u.login = ?1")
    Optional<UserEntity> findUserByLogin(String login);
}
