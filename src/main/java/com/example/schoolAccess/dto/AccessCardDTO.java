package com.example.schoolAccess.dto;

import com.example.schoolAccess.model.AccessCard;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class AccessCardDTO {
    private UUID accessCardId;
    private boolean activated;


    public AccessCardDTO(AccessCard accessCard) {
        this.accessCardId = accessCard.getAccessCardId();
        this.activated = accessCard.isActivated();
    }


    public static List<AccessCardDTO> fromAccessCardList(List<AccessCard> cards) {
        return cards.stream().map(AccessCardDTO::new).toList();
    }
}
