package com.github.Capybara666.flashcards.user;

import com.github.Capybara666.flashcards.user.dtos.UserRequestedDto;
import com.github.Capybara666.flashcards.user.dtos.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserMapper::objectToResponseDto)
                .collect(Collectors.toList());
    }

    public ResponseEntity<UserResponseDto> loginUser(UserRequestedDto userRequestedDto) {
        UserEntity userRequest = UserMapper.requestedDtoToObject(userRequestedDto);
        UserEntity user = userRepository.findUserByLogin(userRequest.getLogin()).orElse(null);
        UserResponseDto userResponseDto = UserMapper.objectToResponseDto(userRequest);
        if(user != null) {
            if (user.getPassword().equals(userRequest.getPassword())) {
                userResponseDto.setIsLoginSuccessful(Boolean.TRUE);
                return new ResponseEntity<>(userResponseDto, HttpStatus.ACCEPTED);
            }
        }
        userResponseDto.setIsLoginSuccessful(Boolean.FALSE);
        return new ResponseEntity<>(userResponseDto, HttpStatus.CONFLICT);
    }
}
