package com.github.Capybara666.flashcards.flashcards;

import com.github.Capybara666.flashcards.flashcards.dtos.FlashcardRequestedDto;
import com.github.Capybara666.flashcards.flashcards.dtos.FlashcardSetRequestedDto;
import com.github.Capybara666.flashcards.flashcards.dtos.FlashcardSetResponseDto;
import com.github.Capybara666.flashcards.users.UserEntity;
import com.github.Capybara666.flashcards.users.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlashcardsService {

    private final FlashcardRepository flashcardRepository;
    private final FlashcardSetRepository flashcardSetRepository;
    private final UserRepository userRepository;

    @Autowired
    public FlashcardsService(FlashcardRepository flashcardRepository,
                             FlashcardSetRepository flashcardSetRepository,
                             UserRepository userRepository) {
        this.flashcardRepository = flashcardRepository;
        this.flashcardSetRepository = flashcardSetRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<List<FlashcardSetResponseDto>> getFlashcardSets(String login) {
        try {
            List<FlashcardSetEntity> flashcardSetEntities = flashcardSetRepository.findAllByLogin(login).orElse(null);
            if (flashcardSetEntities == null) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            } else {
                List<FlashcardSetResponseDto> flashcardSetResponseDtos = flashcardSetEntities.stream()
                        .map(FlashcardSetMapper::objectToResponseDto)
                        .toList();
                return new ResponseEntity<>(flashcardSetResponseDtos, HttpStatus.OK);
            }
        } catch (DataAccessException e) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<FlashcardSetResponseDto> getFlashcardSet(String login, String flashcardSetName) {
        try {
            FlashcardSetEntity flashcardSetEntity = flashcardSetRepository.findOneByLoginAndName(login, flashcardSetName).orElse(null);
            if (flashcardSetEntity == null) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            } else {
                FlashcardSetResponseDto flashcardSetsResponseDto = FlashcardSetMapper.objectToResponseDto(flashcardSetEntity);
                return new ResponseEntity<>(flashcardSetsResponseDto, HttpStatus.OK);
            }
        } catch (DataAccessException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> deleteFlashcardSet(@NotNull FlashcardSetRequestedDto flashcardSetRequestedDto) {
        try {
            FlashcardSetEntity flashcardSetEntity = flashcardSetRepository.findById(flashcardSetRequestedDto.getId()).orElse(null);
            if (flashcardSetEntity == null) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            } else {
                flashcardSetRepository.deleteById(flashcardSetRequestedDto.getId());
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } catch (DataAccessException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> deleteFlashcard(@NotNull FlashcardRequestedDto flashcardRequestedDto) {
        try {
            FlashcardEntity flashcardEntity = flashcardRepository.findById(flashcardRequestedDto.getId()).orElse(null);
            if (flashcardEntity == null) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            } else {
                flashcardRepository.deleteById(flashcardRequestedDto.getId());
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } catch (DataAccessException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> createFlashcardSet(@NotNull FlashcardSetRequestedDto flashcardSetRequestedDto) {
        try {
            FlashcardSetEntity flashcardSetEntity = flashcardSetRepository.findById(flashcardSetRequestedDto.getId()).orElse(null);
            if (flashcardSetEntity != null) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            } else {
                FlashcardSetEntity newFlashcardSet = FlashcardSetMapper.requestedDtoToObject(flashcardSetRequestedDto);
                UserEntity user = userRepository.findById(flashcardSetRequestedDto.getUserId()).orElse(null);
                newFlashcardSet.setUser(user);
                this.flashcardSetRepository.save(newFlashcardSet);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } catch (DataAccessException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> createFlashcard(@NotNull FlashcardRequestedDto flashcardRequestedDto) {
        try {
            FlashcardEntity flashcardEntity = flashcardRepository.findById(flashcardRequestedDto.getId()).orElse(null);
            FlashcardSetEntity flashcardSet = flashcardSetRepository.findById(flashcardRequestedDto.getFlashcardSetId()).orElse(null);
            if (flashcardEntity != null || flashcardSet == null) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
            FlashcardEntity newFlashcard = FlashcardMapper.requestedDtoToObject(flashcardRequestedDto);
            newFlashcard.setFlashcardSet(flashcardSet);
            flashcardRepository.save(newFlashcard);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> updateFlashcardSet(@NotNull FlashcardSetRequestedDto flashcardSetRequestedDto) {
        try {
            FlashcardSetEntity flashcardSetToUpdate = flashcardSetRepository.findById(flashcardSetRequestedDto.getId()).orElse(null);
            if (flashcardSetToUpdate == null) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            } else {
                UserEntity user = userRepository.findById(flashcardSetRequestedDto.getUserId()).orElse(null);
                flashcardSetToUpdate.setUser(user);
                flashcardSetToUpdate.setName(flashcardSetRequestedDto.getName());
                this.flashcardSetRepository.save(flashcardSetToUpdate);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } catch (DataAccessException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Void> updateFlashcard(@NotNull FlashcardRequestedDto flashcardRequestedDto) {
        try {
            FlashcardEntity flashcardToUpdate = flashcardRepository.findById(flashcardRequestedDto.getId()).orElse(null);
            if (flashcardToUpdate == null) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            } else {
                flashcardToUpdate.setFrontContent(flashcardRequestedDto.getFrontContent());
                flashcardToUpdate.setBackContent(flashcardRequestedDto.getBackContent());
                flashcardRepository.save(flashcardToUpdate);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
