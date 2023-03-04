package com.github.Capybara666.flashcards.user.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserResponseDto {
    private String login;
    private String password;
    private Boolean isLoginSuccessful;
}
