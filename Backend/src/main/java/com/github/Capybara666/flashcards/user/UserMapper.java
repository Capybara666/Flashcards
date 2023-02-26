package com.github.Capybara666.flashcards.user;

import com.github.Capybara666.flashcards.user.dtos.UserRequestedDto;
import com.github.Capybara666.flashcards.user.dtos.UserResponseDto;

public class UserMapper {
    public static User requestedDtoToObject(UserRequestedDto userRequestedDto) {
        return User.builder()
                .id(userRequestedDto.getId())
                .login(userRequestedDto.getLogin())
                .password(userRequestedDto.getPassword())
                .build();
    }

    public static UserResponseDto objectToResponseDto(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .login(user.getLogin())
                .password(user.getPassword())
                .build();
    }
}
