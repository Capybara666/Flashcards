package com.github.Capybara666.flashcards.users;

import com.github.Capybara666.flashcards.users.dtos.UserRequestedDto;
import com.github.Capybara666.flashcards.users.dtos.UserResponseDto;
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
    public ResponseEntity<Void> loginUser(@RequestBody UserRequestedDto userRequestedDto) {
        return userService.loginUser(userRequestedDto);
    }

    @PostMapping(path = "/auth/register")
    public ResponseEntity<Void> registerUser(@RequestBody UserRequestedDto userRequestedDto) {
        return userService.registerUser(userRequestedDto);
    }

}
