package com.github.Capybara666.flashcards.users;

import com.github.Capybara666.flashcards.users.dtos.UserRequestedDto;
import com.github.Capybara666.flashcards.users.dtos.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
        UserEntity requestedUser = UserMapper.requestedDtoToObject(userRequestedDto);
        UserResponseDto userResponseDto = UserMapper.objectToResponseDto(requestedUser);
        try {
            ResponseEntity<UserResponseDto> responseEntity;
            UserEntity user = userRepository.findUserByLogin(userRequestedDto.getLogin()).orElse(null);
            if(user != null && user.getPassword().equals(userRequestedDto.getPassword())) {
                userResponseDto.setSuccessfulLogin(true);
                responseEntity = new ResponseEntity<>(userResponseDto, HttpStatus.ACCEPTED);
            }
            else {
                userResponseDto.setSuccessfulLogin(false);
                responseEntity = new ResponseEntity<>(userResponseDto, HttpStatus.CONFLICT);
            }
            return responseEntity;
        } catch (DataAccessException e) {
            userResponseDto.setSuccessfulLogin(false);
            return new ResponseEntity<>(userResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<UserResponseDto> registerUser(UserRequestedDto userRequestedDto) {
        UserEntity requestedUser = UserMapper.requestedDtoToObject(userRequestedDto);
        UserResponseDto userResponseDto = UserMapper.objectToResponseDto(requestedUser);
        try {
            ResponseEntity<UserResponseDto> responseEntity;
            UserEntity user = userRepository.findUserByLogin(userRequestedDto.getLogin()).orElse(null);
            if(user == null) {
                userRepository.save(requestedUser);
                userResponseDto.setRegistered(true);
                responseEntity = new ResponseEntity<>(userResponseDto, HttpStatus.ACCEPTED);
            }
            else {
                userResponseDto.setRegistered(false);
                responseEntity = new ResponseEntity<>(userResponseDto, HttpStatus.CONFLICT);
            }
            return responseEntity;
        } catch (DataAccessException e) {
            userResponseDto.setRegistered(false);
            return new ResponseEntity<>(userResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
