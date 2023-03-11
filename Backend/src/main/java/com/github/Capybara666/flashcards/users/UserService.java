package com.github.Capybara666.flashcards.users;

import com.github.Capybara666.flashcards.users.dtos.UserRequestedDto;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<Void> loginUser(@NotNull UserRequestedDto userRequestedDto) {
        try {
            UserEntity user = userRepository.findUserByLogin(userRequestedDto.getLogin()).orElse(null);
            if (user != null && user.getPassword().equals(userRequestedDto.getPassword())) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        } catch (DataAccessException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> registerUser(UserRequestedDto userRequestedDto) {
        try {
            UserEntity requestedUser = UserMapper.requestedDtoToObject(userRequestedDto);
            UserEntity user = userRepository.findUserByLogin(userRequestedDto.getLogin()).orElse(null);
            if (user == null) {
                userRepository.save(requestedUser);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        } catch (DataAccessException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
