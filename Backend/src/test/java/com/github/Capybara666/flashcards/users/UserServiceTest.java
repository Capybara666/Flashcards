package com.github.Capybara666.flashcards.users;

import com.github.Capybara666.flashcards.users.dtos.UserRequestedDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.datasource.lookup.DataSourceLookupFailureException;

import java.util.Optional;


@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        UserEntity user1 = UserEntity.builder()
                .id(1)
                .login("login1")
                .password("password1")
                .build();

        Mockito.when(userRepository.findUserByLogin("login1"))
                .thenReturn(Optional.ofNullable(user1));

        Mockito.when(userRepository.findUserByLogin("login2"))
                .thenReturn(Optional.empty());

        Mockito.when(userRepository.findUserByLogin("login3"))
                .thenThrow(new DataSourceLookupFailureException(""));
    }

    @Test
    void whenUserExistsAndCorrectPassword_loginUserShouldReturnOk() {
        //Given
        UserRequestedDto requestedUser = UserRequestedDto.builder()
                .login("login1")
                .password("password1")
                .build();

        //When
        ResponseEntity<Void> response = userService.loginUser(requestedUser);

        //Then
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void whenUserNotExistsOrIncorrectPassword_loginUserShouldReturnConflict() {
        //Given
        UserRequestedDto requestedUser1 = UserRequestedDto.builder()
                .login("login1")
                .password("password2")
                .build();

        UserRequestedDto requestedUser2 = UserRequestedDto.builder()
                .login("login2")
                .password("password1")
                .build();

        UserRequestedDto requestedUser3 = UserRequestedDto.builder()
                .login("login2")
                .password("password2")
                .build();

        //When
        ResponseEntity<Void> response1 = userService.loginUser(requestedUser1);
        ResponseEntity<Void> response2 = userService.loginUser(requestedUser2);
        ResponseEntity<Void> response3 = userService.loginUser(requestedUser3);

        //Then
        Assertions.assertEquals(HttpStatus.CONFLICT, response1.getStatusCode());
        Assertions.assertEquals(HttpStatus.CONFLICT, response2.getStatusCode());
        Assertions.assertEquals(HttpStatus.CONFLICT, response3.getStatusCode());
    }

    @Test
    void whenRepositoryThrowsException_loginUserShouldReturnInternalServerError() {
        //Given
        UserRequestedDto requestedUser = UserRequestedDto.builder()
                .login("login3")
                .password("password3")
                .build();

        //When
        ResponseEntity<Void> response = userService.loginUser(requestedUser);

        //Then
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void whenUserExists_registerUserShouldReturnConflict() {
        //Given
        UserRequestedDto requestedUser = UserRequestedDto.builder()
                .login("login1")
                .password("password1")
                .build();

        //When
        ResponseEntity<Void> response = userService.registerUser(requestedUser);

        //Then
        Assertions.assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    void whenUserNotExists_registerUserShouldReturnOk() {
        //Given
        UserRequestedDto requestedUser = UserRequestedDto.builder()
                .login("login2")
                .password("password2")
                .build();

        //When
        ResponseEntity<Void> response = userService.registerUser(requestedUser);

        //Then
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void whenRepositoryThrowsException_registerUserShouldReturnInternalServerError() {
        //Given
        UserRequestedDto requestedUser = UserRequestedDto.builder()
                .login("login3")
                .password("password3")
                .build();

        //When
        ResponseEntity<Void> response = userService.registerUser(requestedUser);

        //Then
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

}