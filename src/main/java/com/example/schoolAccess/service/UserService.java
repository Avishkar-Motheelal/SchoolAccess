package com.example.schoolAccess.service;

import com.example.schoolAccess.dto.UserRequestDTO;
import com.example.schoolAccess.dto.UserUpdateDTO;
import com.example.schoolAccess.exception.NotFoundException;
import com.example.schoolAccess.model.ContactDetail;
import com.example.schoolAccess.model.Role;
import com.example.schoolAccess.model.User;
import com.example.schoolAccess.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;


    public User getUserById(int id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("Unable to find user with specified ID"));
    }


    public User createOrUpdate(User user) {
        return userRepository.save(user);
    }


    public User create(UserRequestDTO userRequestDTO) {
        String roleName = userRequestDTO.getRoleName();
        String firstName = userRequestDTO.getFirstName();
        String lastName = userRequestDTO.getLastName();

        Role role = roleService.getByName(roleName);
        ContactDetail contactDetail = userRequestDTO.getContactDetail().toContactDetail();
        User user = new User(firstName, lastName, contactDetail, role);
        return this.createOrUpdate(user);
    }


    public User update(UserUpdateDTO user, int id) {
        User originalUser = getUserById(id);
        Optional.ofNullable(user.getFirstName()).ifPresent(originalUser::setFirstName);
        Optional.ofNullable(user.getLastName()).ifPresent(originalUser::setLastName);
        Optional.ofNullable(user.getRoleName()).ifPresent(r -> originalUser.setRole(roleService.getByName(r)));

        return createOrUpdate(originalUser);
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
