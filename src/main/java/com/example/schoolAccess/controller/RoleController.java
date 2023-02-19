package com.example.schoolAccess.controller;

import com.example.schoolAccess.model.Role;
import com.example.schoolAccess.service.RoleService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Roles")
public class RoleController {
    private final RoleService roleService;


    @ApiResponse(responseCode = "200", description = "Returns a list of available roles")
    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleService.getAll();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }


    @ApiResponse(responseCode = "200", description = "The role was created successfully")
    @PostMapping("/roles")
    public ResponseEntity<Role> createRole(String roleName) {
        Role role = new Role(roleName);
        role = roleService.createOrUpdate(role);
        return new ResponseEntity<>(role, HttpStatus.CREATED);
    }


    @PatchMapping("/roles")
    @ApiResponses(
        {
            @ApiResponse(responseCode = "200", description = "Updating role name was successful"),
            @ApiResponse(responseCode = "400", description = "Role name was not specified"),
            @ApiResponse(responseCode = "404", description = "Role was not found")
        }
    )
    public ResponseEntity<Role> updateRole(Role role) {
        role = roleService.update(role);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }
}
