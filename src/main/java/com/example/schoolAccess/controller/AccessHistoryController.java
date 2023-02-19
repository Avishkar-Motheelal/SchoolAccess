package com.example.schoolAccess.controller;

import com.example.schoolAccess.dto.AccessHistoryDTO;
import com.example.schoolAccess.model.AccessHistory;
import com.example.schoolAccess.service.AccessHistoryService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Access History")
public class AccessHistoryController {
    private final AccessHistoryService accessHistoryService;


    @GetMapping(path = "/users/{userId}/access/history/")
    @ApiResponses(
        {
            @ApiResponse(responseCode = "200", description = "Returns a list of the user's access history"),
            @ApiResponse(responseCode = "404", description = "Specified user was not found")
        }
    )
    public ResponseEntity<List<AccessHistoryDTO>> getUserHistory(@PathVariable int userId) {
        List<AccessHistory> history = accessHistoryService.getHistoryByUserId(userId);
        return new ResponseEntity<>(AccessHistoryDTO.fromAccessHistoryList(history), HttpStatus.OK);
    }
}
