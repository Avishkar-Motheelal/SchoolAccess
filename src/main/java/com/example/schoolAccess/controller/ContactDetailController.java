package com.example.schoolAccess.controller;

import com.example.schoolAccess.dto.ContactDetailDTO;
import com.example.schoolAccess.model.ContactDetail;
import com.example.schoolAccess.model.User;
import com.example.schoolAccess.service.ContactDetailService;
import com.example.schoolAccess.service.UserService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Contact Details")
public class ContactDetailController {
    private final ContactDetailService contactDetailService;
    private final UserService userService;


    @PatchMapping(path = "/users/{userId}/contact")
    @ApiResponses(
        {
            @ApiResponse(responseCode = "200", description = "Updating contact details was successful"),
            @ApiResponse(responseCode = "404", description = "User was not found"),
            @ApiResponse(responseCode = "400", description = "Invalid information was specified")
        }
    )
    public ResponseEntity<ContactDetailDTO> updateContactDetail(@PathVariable int userId, ContactDetailDTO contactDetailDTO) {
        User user = userService.getUserById(userId);
        ContactDetail contactDetail = contactDetailService.update(user, contactDetailDTO);

        return new ResponseEntity<>(new ContactDetailDTO(contactDetail), HttpStatus.OK);
    }
}
