package com.github.Capybara666.flashcards.users.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserResponseDto {
    private String login;
    private String password;
    private Boolean successfulLogin;
    private Boolean registered;
}
