package com.example.schoolAccess.dto;

import com.example.schoolAccess.model.Role;
import lombok.Data;

@Data
public class RoleDTO {
    private String roleName;


    public RoleDTO(Role role) {
        this.roleName = role.getRoleName();
    }
}
