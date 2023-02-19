package com.example.schoolAccess.service;

import com.example.schoolAccess.exception.NotFoundException;
import com.example.schoolAccess.exception.ValidationException;
import com.example.schoolAccess.model.Role;
import com.example.schoolAccess.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;


    public Role getByName(String roleName) {
        return roleRepository.findByRoleNameIgnoreCase(roleName).orElseThrow(() -> new NotFoundException("Role with specified name does not exist"));
    }


    public List<Role> getAll() {
        return roleRepository.findAll();
    }


    public Role createOrUpdate(Role role) {
        return roleRepository.save(role);
    }


    public Role getById(int id) {
        return roleRepository.findById(id).orElseThrow(() -> new NotFoundException("Role with specified id not found"));
    }


    public Role update(Role role) {
        // TODO
        getById(role.getRoleId());
        if (role.getRoleName() == null) throw new ValidationException("Role name should be specified");
        return createOrUpdate(role);
    }
}
