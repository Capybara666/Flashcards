package com.github.Capybara666.flashcards.flashcards;

import com.github.Capybara666.flashcards.flashcards.dtos.FlashcardRequestedDto;
import com.github.Capybara666.flashcards.flashcards.dtos.FlashcardSetRequestedDto;
import com.github.Capybara666.flashcards.flashcards.dtos.FlashcardSetResponseDto;
import com.github.Capybara666.flashcards.users.UserEntity;
import com.github.Capybara666.flashcards.users.UserRepository;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class FlashcardsServiceTest {

    @Autowired
    private FlashcardsService flashcardsService;
    @MockBean
    private FlashcardRepository flashcardRepository;
    @MockBean
    private FlashcardSetRepository flashcardSetRepository;
    @MockBean
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        UserEntity user1 = UserEntity.builder()
                .id(1L)
                .login("login1")
                .password("password1")
                .build();

        UserEntity user2 = UserEntity.builder()
                .id(2L)
                .login("login2")
                .password("password2")
                .build();

        UserEntity user3 = UserEntity.builder()
                .id(3L)
                .login("login3")
                .password("password3")
                .build();

        FlashcardSetEntity flashcardSet1 = FlashcardSetEntity.builder()
                .id(1L)
                .name("name1")
                .user(user1)
                .build();

        FlashcardSetEntity flashcardSet2 = FlashcardSetEntity.builder()
                .id(2L)
                .name("name2")
                .user(user1)
                .build();

        FlashcardSetEntity flashcardSet3 = FlashcardSetEntity.builder()
                .id(3L)
                .name("name3")
                .user(user2)
                .build();

        FlashcardEntity flashcard1 = FlashcardEntity.builder()
                .id(1L)
                .frontContent("front1")
                .backContent("back1")
                .flashcardSet(flashcardSet1)
                .build();

        FlashcardEntity flashcard2 = FlashcardEntity.builder()
                .id(2L)
                .frontContent("front2")
                .backContent("back2")
                .flashcardSet(flashcardSet1)
                .build();

        FlashcardEntity flashcard3 = FlashcardEntity.builder()
                .id(3L)
                .frontContent("front3")
                .backContent("back3")
                .flashcardSet(flashcardSet2)
                .build();

        ArrayList<FlashcardEntity> flashcardSet1Flashcards = new ArrayList<>();
        flashcardSet1Flashcards.add(flashcard1);
        flashcardSet1Flashcards.add(flashcard2);
        flashcardSet1.setFlashcards(flashcardSet1Flashcards);

        ArrayList<FlashcardEntity> flashcardSet2Flashcards = new ArrayList<>();
        flashcardSet2Flashcards.add(flashcard3);
        flashcardSet2.setFlashcards(flashcardSet2Flashcards);

        ArrayList<FlashcardEntity> flashcardSet3Flashcards = new ArrayList<>();
        flashcardSet3.setFlashcards(flashcardSet3Flashcards);

        ArrayList<FlashcardSetEntity> user1FlashcardSets = new ArrayList<>();
        user1FlashcardSets.add(flashcardSet1);
        user1FlashcardSets.add(flashcardSet2);
        user1.setFlashcardSets(user1FlashcardSets);

        ArrayList<FlashcardSetEntity> user2FlashcardSets = new ArrayList<>();
        user2FlashcardSets.add(flashcardSet3);
        user2.setFlashcardSets(user2FlashcardSets);

        ArrayList<FlashcardSetEntity> user3FlashcardSets = new ArrayList<>();
        user3.setFlashcardSets(user3FlashcardSets);

        Mockito.when(userRepository.findById(1L))
                .thenReturn(Optional.of(user1));

        Mockito.when(userRepository.findById(2L))
                .thenReturn(Optional.of(user2));

        Mockito.when(userRepository.findById(3L))
                .thenReturn(Optional.of(user3));

        Mockito.when(userRepository.findById(4L))
                .thenReturn(Optional.empty());

        Mockito.when(flashcardSetRepository.findById(1L))
                .thenReturn(Optional.of(flashcardSet1));

        Mockito.when(flashcardSetRepository.findById(2L))
                .thenReturn(Optional.of(flashcardSet2));

        Mockito.when(flashcardSetRepository.findById(3L))
                .thenReturn(Optional.of(flashcardSet3));

        Mockito.when(flashcardSetRepository.findById(4L))
                .thenReturn(Optional.empty());

        Mockito.when(flashcardSetRepository.findById(5L))
                .thenThrow(new DataSourceLookupFailureException(""));

        Mockito.when(flashcardSetRepository.findAllByLogin("login1"))
                .thenReturn(Optional.of(user1FlashcardSets));

        Mockito.when(flashcardSetRepository.findAllByLogin("login2"))
                .thenReturn(Optional.of(user2FlashcardSets));

        Mockito.when(flashcardSetRepository.findAllByLogin("login3"))
                .thenReturn(Optional.of(user3FlashcardSets));

        Mockito.when(flashcardSetRepository.findAllByLogin("login5"))
                        .thenThrow(new DataSourceLookupFailureException(""));

        Mockito.when(flashcardSetRepository.findOneByLoginAndName("login1", "name1"))
                .thenReturn(Optional.of(flashcardSet1));

        Mockito.when(flashcardSetRepository.findOneByLoginAndName("login1", "name3"))
                .thenReturn(Optional.empty());

        Mockito.when(flashcardSetRepository.findOneByLoginAndName("login3", "name1"))
                .thenReturn(Optional.empty());

        Mockito.when(flashcardSetRepository.findOneByLoginAndName("login3", "name3"))
                .thenReturn(Optional.empty());

        Mockito.when(flashcardSetRepository.findOneByLoginAndName("login4", "name4"))
                .thenThrow(new DataSourceLookupFailureException(""));

        Mockito.when(flashcardRepository.findById(1L))
                .thenReturn(Optional.of(flashcard1));

        Mockito.when(flashcardRepository.findById(2L))
                .thenReturn(Optional.of(flashcard2));

        Mockito.when(flashcardRepository.findById(3L))
                .thenReturn(Optional.of(flashcard3));

        Mockito.when(flashcardRepository.findById(4L))
                .thenReturn(Optional.empty());

        Mockito.when(flashcardRepository.findById(5L))
                .thenThrow(new DataSourceLookupFailureException(""));


    }

    @Test
    void whenUserNotExists_getFlashcardSetsShouldReturnConflict() {
        //Given
        String requestedLogin = "login4";

        //When
        ResponseEntity<List<FlashcardSetResponseDto>> response = flashcardsService.getFlashcardSets(requestedLogin);

        //Then
        Assertions.assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    void whenRepositoryThrowsException_getFlashcardSetsShouldReturnInternalServerError() {
        //Given
        String requestedLogin = "login5";

        //When
        ResponseEntity<List<FlashcardSetResponseDto>> response = flashcardsService.getFlashcardSets(requestedLogin);

        //Then
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void whenNoFlashcardSetsForUser_getFlashcardSetsShouldReturnOkWithEmptyList() {
        //Given
        String requestedLogin = "login3";

        //When
        ResponseEntity<List<FlashcardSetResponseDto>> response = flashcardsService.getFlashcardSets(requestedLogin);

        //Then
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertTrue(response.getBody().isEmpty());
    }

    @Test
    void whenUserExists_getFlashcardSetsShouldReturnOkWithAllTheSetsOfUser() {
        //Given
        String requestedLogin = "login1";

        //When
        ResponseEntity<List<FlashcardSetResponseDto>> response = flashcardsService.getFlashcardSets(requestedLogin);

        //Then
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());

        List<FlashcardSetResponseDto> result = response.getBody();
        Assertions.assertEquals(2, result.size());
    }

    @Test
    void whenSetNotExists_getFlashcardSetShouldReturnConflict() {
        //Given
        String existingLogin = "login1";
        String nonExistingLogin = "login3";
        String existingSet = "name1";
        String nonExistingSet = "name3";

        //When
        ResponseEntity<FlashcardSetResponseDto> response1 = flashcardsService.getFlashcardSet(existingLogin, nonExistingLogin);
        ResponseEntity<FlashcardSetResponseDto> response2 = flashcardsService.getFlashcardSet(nonExistingLogin, existingSet);
        ResponseEntity<FlashcardSetResponseDto> response3 = flashcardsService.getFlashcardSet(nonExistingLogin, nonExistingSet);

        //Then
        Assertions.assertEquals(HttpStatus.CONFLICT, response1.getStatusCode());
        Assertions.assertEquals(HttpStatus.CONFLICT, response2.getStatusCode());
        Assertions.assertEquals(HttpStatus.CONFLICT, response3.getStatusCode());
    }

    @Test
    void whenRepositoryThrowsException_getFlashcardSetShouldReturnInternalServerError() {
        //Given
        String requestedLogin = "login4";
        String requestedSet = "name4";

        //When
        ResponseEntity<FlashcardSetResponseDto> response = flashcardsService.getFlashcardSet(requestedLogin, requestedSet);

        //Then
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void whenSetExists_getFlashcardSetShouldReturnOkWithTheSet() {
        //Given
        String requestedLogin = "login1";
        String requestedSet = "name1";

        //When
        ResponseEntity<FlashcardSetResponseDto> response = flashcardsService.getFlashcardSet(requestedLogin, requestedSet);

        //Then
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());

        FlashcardSetResponseDto result = response.getBody();
        Assertions.assertEquals(requestedSet, result.getName());
        Assertions.assertEquals(2, result.getFlashcards().size());
    }

    @Test
    void whenIdExists_deleteFlashcardSetShouldReturnOk() {
        //Given
        FlashcardSetRequestedDto requestedDto = FlashcardSetRequestedDto.builder()
                .id(1)
                .build();

        //When
        ResponseEntity<Void> response = flashcardsService.deleteFlashcardSet(requestedDto);

        //Then
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void whenIdNotExists_deleteFlashcardSetShouldReturnConflict() {
        //Given
        FlashcardSetRequestedDto requestedDto = FlashcardSetRequestedDto.builder()
                .id(4)
                .build();

        //When
        ResponseEntity<Void> response = flashcardsService.deleteFlashcardSet(requestedDto);

        //Then
        Assertions.assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    void whenRepositoryThrowsException_deleteFlashcardSetShouldReturnInternalServerError() {
        //Given
        FlashcardSetRequestedDto requestedDto = FlashcardSetRequestedDto.builder()
                .id(5)
                .build();

        //When
        ResponseEntity<Void> response = flashcardsService.deleteFlashcardSet(requestedDto);

        //Then
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void whenIdExists_deleteFlashcardShouldReturnOk() {
        //Given
        FlashcardRequestedDto requestedDto = FlashcardRequestedDto.builder()
                .id(1)
                .build();

        //When
        ResponseEntity<Void> response = flashcardsService.deleteFlashcard(requestedDto);

        //Then
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void whenIdNotExists_deleteFlashcardShouldReturnConflict() {
        //Given
        FlashcardRequestedDto requestedDto = FlashcardRequestedDto.builder()
                .id(4)
                .build();

        //When
        ResponseEntity<Void> response = flashcardsService.deleteFlashcard(requestedDto);

        //Then
        Assertions.assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    void whenRepositoryThrowsException_deleteFlashcardShouldReturnInternalServerError() {
        //Given
        FlashcardRequestedDto requestedDto = FlashcardRequestedDto.builder()
                .id(5)
                .build();

        //When
        ResponseEntity<Void> response = flashcardsService.deleteFlashcard(requestedDto);

        //Then
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void whenIdExists_createFlashcardSetShouldReturnConflict() {
        //Given
        FlashcardSetRequestedDto requestedDto = FlashcardSetRequestedDto.builder()
                .id(1)
                .build();

        //When
        ResponseEntity<Void> response = flashcardsService.createFlashcardSet(requestedDto);

        //Then
        Assertions.assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    void whenIdNotExists_createFlashcardSetShouldReturnOk() {
        //Given
        FlashcardSetRequestedDto requestedDto = FlashcardSetRequestedDto.builder()
                .id(4)
                .build();

        //When
        ResponseEntity<Void> response = flashcardsService.createFlashcardSet(requestedDto);

        //Then
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void whenRepositoryThrowsException_createFlashcardSetShouldReturnInternalServerError() {
        //Given
        FlashcardSetRequestedDto requestedDto = FlashcardSetRequestedDto.builder()
                .id(5)
                .build();

        //When
        ResponseEntity<Void> response = flashcardsService.createFlashcardSet(requestedDto);

        //Then
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void whenIdExistsOrNonExistingSetIdProvided_createFlashcardShouldReturnConflict() {
        //Given
        FlashcardRequestedDto requestedDto1 = FlashcardRequestedDto.builder()
                .id(1)
                .flashcardSetId(4)
                .build();

        FlashcardRequestedDto requestedDto2 = FlashcardRequestedDto.builder()
                .id(4)
                .flashcardSetId(4)
                .build();

        FlashcardRequestedDto requestedDto3 = FlashcardRequestedDto.builder()
                .id(1)
                .flashcardSetId(4)
                .build();

        //When
        ResponseEntity<Void> response1 = flashcardsService.createFlashcard(requestedDto1);
        ResponseEntity<Void> response2 = flashcardsService.createFlashcard(requestedDto2);
        ResponseEntity<Void> response3 = flashcardsService.createFlashcard(requestedDto3);

        //Then
        Assertions.assertEquals(HttpStatus.CONFLICT, response1.getStatusCode());
        Assertions.assertEquals(HttpStatus.CONFLICT, response2.getStatusCode());
        Assertions.assertEquals(HttpStatus.CONFLICT, response3.getStatusCode());
    }

    @Test
    void whenIdNotExistsAndExistingSetIdProvided_createFlashcardShouldReturnOk() {
        //Given
        FlashcardRequestedDto requestedDto = FlashcardRequestedDto.builder()
                .id(4)
                .flashcardSetId(1)
                .build();

        //When
        ResponseEntity<Void> response = flashcardsService.createFlashcard(requestedDto);

        //Then
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void whenRepositoryThrowsException_createFlashcardShouldReturnInternalServerError() {
        //Given
        FlashcardRequestedDto requestedDto = FlashcardRequestedDto.builder()
                .id(5)
                .build();

        //When
        ResponseEntity<Void> response = flashcardsService.createFlashcard(requestedDto);

        //Then
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void whenIdExists_updateFlashcardSetShouldReturnOk() {
        //Given
        FlashcardSetRequestedDto requestedDto = FlashcardSetRequestedDto.builder()
                .id(1)
                .build();

        //When
        ResponseEntity<Void> response = flashcardsService.updateFlashcardSet(requestedDto);

        //Then
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void whenIdNotExists_updateFlashcardSetShouldReturnConflict() {
        //Given
        FlashcardSetRequestedDto requestedDto = FlashcardSetRequestedDto.builder()
                .id(4)
                .build();

        //When
        ResponseEntity<Void> response = flashcardsService.updateFlashcardSet(requestedDto);

        //Then
        Assertions.assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    void whenRepositoryThrowsException_updateFlashcardSetShouldReturnInternalServerError() {
        //Given
        FlashcardSetRequestedDto requestedDto = FlashcardSetRequestedDto.builder()
                .id(5)
                .build();

        //When
        ResponseEntity<Void> response = flashcardsService.updateFlashcardSet(requestedDto);

        //Then
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void whenIdExists_updateFlashcardShouldReturnOk() {
        //Given
        FlashcardRequestedDto requestedDto = FlashcardRequestedDto.builder()
                .id(1)
                .build();

        //When
        ResponseEntity<Void> response = flashcardsService.updateFlashcard(requestedDto);

        //Then
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void whenIdNotExists_updateFlashcardShouldReturnConflict() {
        //Given
        FlashcardRequestedDto requestedDto = FlashcardRequestedDto.builder()
                .id(4)
                .build();

        //When
        ResponseEntity<Void> response = flashcardsService.updateFlashcard(requestedDto);

        //Then
        Assertions.assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    void whenRepositoryThrowsException_updateFlashcardShouldReturnInternalServerError() {
        //Given
        FlashcardRequestedDto requestedDto = FlashcardRequestedDto.builder()
                .id(5)
                .build();

        //When
        ResponseEntity<Void> response = flashcardsService.updateFlashcard(requestedDto);

        //Then
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

}