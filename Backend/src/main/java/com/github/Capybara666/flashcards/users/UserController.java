package com.github.Capybara666.flashcards.users;

import com.github.Capybara666.flashcards.users.dtos.UserRequestedDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
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
