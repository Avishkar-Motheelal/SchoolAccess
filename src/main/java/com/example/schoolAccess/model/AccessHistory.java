package com.example.schoolAccess.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class AccessHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accessHistoryId;
    @ManyToOne
    @JoinColumn(name = "accessCardID")
    @JsonManagedReference
    private AccessCard accessCard;
    private OffsetDateTime dateUsed;
    private boolean wasAllowed;


    public AccessHistory(AccessCard accessCard, boolean wasAllowed) {
        this.dateUsed = OffsetDateTime.now();
        this.accessCard = accessCard;
        this.wasAllowed = wasAllowed;
    }
}
