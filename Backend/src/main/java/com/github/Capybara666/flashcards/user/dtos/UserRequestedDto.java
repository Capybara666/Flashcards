package com.github.Capybara666.flashcards.user.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserRequestedDto {
    private String login;
    private String password;
}
