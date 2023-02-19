package com.example.schoolAccess.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Users")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String firstName;
    private String lastName;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ContactDetailsID")
    private ContactDetail contactDetail;

    @ManyToOne
    @JoinColumn(name = "RoleID")
    private Role role;

    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private List<AccessCard> accessCards = new ArrayList<>();


    public User(String firstName, String lastName, ContactDetail contactDetail, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactDetail = contactDetail;
        this.role = role;
    }
}
