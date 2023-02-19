package com.example.schoolAccess.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Roles")
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleId;
    private String roleName;
    @OneToMany(mappedBy = "role")
    @JsonIgnore
    private List<User> user = new ArrayList<>();


    public Role(String roleName) {
        this.roleName = roleName;
    }
}
