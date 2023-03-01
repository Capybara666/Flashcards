package com.github.Capybara666.flashcards.user;

import com.github.Capybara666.flashcards.user.dtos.UserRequestedDto;
import com.github.Capybara666.flashcards.user.dtos.UserResponseDto;

import java.util.Objects;

public class UserMapper {
    public static UserEntity requestedDtoToObject (UserRequestedDto userRequestedDto) {
        Objects.requireNonNull(userRequestedDto);
        return UserEntity.builder()
                .id(userRequestedDto.getId())
                .login(userRequestedDto.getLogin())
                .password(userRequestedDto.getPassword())
                .build();
    }

    public static UserResponseDto objectToResponseDto(UserEntity userEntity) {
        Objects.requireNonNull(userEntity);
        return UserResponseDto.builder()
                .id(userEntity.getId())
                .login(userEntity.getLogin())
                .password(userEntity.getPassword())
                .build();
    }
}
