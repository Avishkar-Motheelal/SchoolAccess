package com.example.schoolAccess.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "AccessCards")
//@NamedEntityGraph(
//    name = "newCard",
//    attributeNodes = {
//        @NamedAttributeNode(value = "accessCardId"),
//        @NamedAttributeNode(value = "user", subgraph = "userId")
//    },
//    subgraphs = {
//        @NamedSubgraph(
//            name = "userId",
//            attributeNodes = @NamedAttributeNode(value = "userId")
//        )
//    }
//)
public class AccessCard {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uuid")
    private UUID accessCardId;
    @ManyToOne()
    @JoinColumn(name = "UserID")
    private User user;
    private boolean activated;
    @OneToMany(mappedBy = "accessCard")
    @JsonBackReference
    private List<AccessHistory> accessHistoryList = new ArrayList<>();


    public AccessCard(User user, boolean activated) {
        this.user = user;
        this.activated = activated;
    }
}
