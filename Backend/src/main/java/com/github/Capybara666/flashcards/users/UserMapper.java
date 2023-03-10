package com.github.Capybara666.flashcards.users;

import com.github.Capybara666.flashcards.users.dtos.UserRequestedDto;
import com.github.Capybara666.flashcards.users.dtos.UserResponseDto;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class UserMapper {
    public static UserEntity requestedDtoToObject(@NotNull UserRequestedDto userRequestedDto) {
        return UserEntity.builder()
                .login(userRequestedDto.getLogin())
                .password(userRequestedDto.getPassword())
                .build();
    }

    public static UserResponseDto objectToResponseDto(@NotNull UserEntity userEntity) {
        Objects.requireNonNull(userEntity);
        return UserResponseDto.builder()
                .login(userEntity.getLogin())
                .password(userEntity.getPassword())
                .build();
    }

}
