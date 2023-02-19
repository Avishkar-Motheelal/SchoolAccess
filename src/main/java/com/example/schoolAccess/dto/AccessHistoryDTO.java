package com.example.schoolAccess.dto;

import com.example.schoolAccess.model.AccessCard;
import com.example.schoolAccess.model.AccessHistory;
import com.example.schoolAccess.model.User;
import lombok.Data;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Data
public class AccessHistoryDTO {
    private UUID accessCardId;
    private String dateTime;
    private UserResponseDTO user;


    public AccessHistoryDTO(AccessHistory accessHistory) {
        User user = accessHistory.getAccessCard().getUser();
        AccessCard accessCard = accessHistory.getAccessCard();
        this.accessCardId = accessCard.getAccessCardId();
        this.user = new UserResponseDTO(user);
        this.dateTime = accessHistory.getDateUsed().format(DateTimeFormatter.ISO_DATE_TIME);
    }


    public static List<AccessHistoryDTO> fromAccessHistoryList(List<AccessHistory> accessHistoryList) {
        return accessHistoryList.stream().map(AccessHistoryDTO::new).toList();
    }
}
