package com.example.schoolAccess.controller;

import com.example.schoolAccess.dto.AccessCardDTO;
import com.example.schoolAccess.model.AccessCard;
import com.example.schoolAccess.model.AccessHistory;
import com.example.schoolAccess.model.User;
import com.example.schoolAccess.service.AccessCardService;
import com.example.schoolAccess.service.AccessHistoryService;
import com.example.schoolAccess.service.UserService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Access Cards")
public class AccessCardController {
    private final AccessCardService accessCardService;
    private final UserService userService;
    private final AccessHistoryService accessHistoryService;


    @PostMapping(path = "/users/{userId}/access/card")
    @ApiResponses(
        {
            @ApiResponse(responseCode = "201", description = "Access card was created successfully"),
            @ApiResponse(responseCode = "404", description = "An invalid user was specified")
        }
    )
    public ResponseEntity<AccessCardDTO> createCard(@PathVariable int userId) {
        User user = userService.getUserById(userId);
        AccessCard accessCard = new AccessCard(user, true);
        accessCard = accessCardService.createOrUpdate(accessCard);
        return new ResponseEntity<>(new AccessCardDTO(accessCard), HttpStatus.CREATED);
    }


    @PostMapping(path = "/access/auth/")
    @ApiResponses(
        {
            @ApiResponse(responseCode = "200", description = "Access granted"),
            @ApiResponse(responseCode = "403", description = "Access denied")
        }
    )
    public ResponseEntity<?> authorizeEntry(String accessCardId) {
        HttpStatus status = HttpStatus.FORBIDDEN;
        boolean wasAllowed = false;
        AccessCard accessCard = accessCardService.getById(accessCardId);

        if (accessCardService.isActiveCard(accessCard)) {
            status = HttpStatus.OK;
            wasAllowed = true;
        }
        AccessHistory accessHistory = new AccessHistory(accessCard, wasAllowed);
        accessHistoryService.create(accessHistory);

        return new ResponseEntity<>(status);
    }


    @DeleteMapping(path = "/access/card")
    @ApiResponses(
        {
            @ApiResponse(responseCode = "204", description = "Successfully disabled access card"),
            @ApiResponse(responseCode = "404", description = "Access card was not found")
        }
    )
    public ResponseEntity<?> disableCard(String accessCardId) {
        accessCardService.disable(accessCardId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping(path = "/users/{userId}/access/card")
    @ApiResponses()
    public ResponseEntity<List<AccessCardDTO>> getAllCards(@PathVariable int userId) {
        List<AccessCard> cards = accessCardService.getAllByUser(userId);
        return new ResponseEntity<>(AccessCardDTO.fromAccessCardList(cards), HttpStatus.OK);
    }
}

