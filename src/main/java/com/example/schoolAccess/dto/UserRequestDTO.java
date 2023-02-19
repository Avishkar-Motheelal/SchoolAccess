package com.example.schoolAccess.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserRequestDTO {
    @NotNull(message = "First name is required")
    private String firstName;
    @NotNull(message = "Last name is required")
    private String lastName;
    @NotNull(message = "Role is required")
    private String roleName;
    @NotNull(message = "Phone number is required")
    private ContactDetailDTO contactDetail;
}
