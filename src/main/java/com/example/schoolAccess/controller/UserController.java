package com.example.schoolAccess.controller;

import com.example.schoolAccess.dto.UserRequestDTO;
import com.example.schoolAccess.dto.UserResponseDTO;
import com.example.schoolAccess.dto.UserUpdateDTO;
import com.example.schoolAccess.model.User;
import com.example.schoolAccess.service.UserService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Users")
public class UserController {

    private final UserService userService;


    @PostMapping(path = "/users")
    @ApiResponses(
        {
            @ApiResponse(responseCode = "200", description = "New user was created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid information was specified")
        }
    )
    public ResponseEntity<UserResponseDTO> registerUser(@Valid UserRequestDTO userRequestDTO) {
        User user = userService.create(userRequestDTO);
        return new ResponseEntity<>(new UserResponseDTO(user), HttpStatus.CREATED);
    }


    @GetMapping(path = "/users/{id}")
    @ApiResponses(
        {
            @ApiResponse(responseCode = "200", description = "Returns a user"),
            @ApiResponse(responseCode = "404", description = "Specified user not found")
        }
    )
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable int id) {
        User user = userService.getUserById(id);
        return new ResponseEntity<>(new UserResponseDTO(user), HttpStatus.OK);
    }


    @PatchMapping(path = "/users")
    @ApiResponses(
        {
            @ApiResponse(responseCode = "200", description = "Updating user was successful"),
            @ApiResponse(responseCode = "404", description = "User was not found"),
            @ApiResponse(responseCode = "400", description = "Invalid information was specified")
        }
    )
    public ResponseEntity<UserResponseDTO> updateUser(@NotNull int userId, UserUpdateDTO userUpdateDTO) {
        User updatedUser = userService.update(userUpdateDTO, userId);
        return new ResponseEntity<>(new UserResponseDTO(updatedUser), HttpStatus.OK);
    }


    @GetMapping(path = "/users")
    @ApiResponses(
        {
            @ApiResponse(responseCode = "200", description = "Returns a list of users"),
        }
    )
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        List<UserResponseDTO> userResponseDTOS = UserResponseDTO.fromUserList(users);
        return new ResponseEntity<>(userResponseDTOS, HttpStatus.OK);
    }
}


