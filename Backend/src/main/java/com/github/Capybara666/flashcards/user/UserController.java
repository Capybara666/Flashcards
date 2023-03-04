package com.github.Capybara666.flashcards.user;

import com.github.Capybara666.flashcards.user.dtos.UserRequestedDto;
import com.github.Capybara666.flashcards.user.dtos.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/users")
    public List<UserResponseDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping(path = "/auth/login")
    public ResponseEntity<UserResponseDto> loginUser(@RequestBody UserRequestedDto userRequestedDto) {
        return userService.loginUser(userRequestedDto);
    }

}
