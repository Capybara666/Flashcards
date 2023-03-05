package com.github.Capybara666.flashcards.users.dtos;

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
