package com.example.schoolAccess.service;

import com.example.schoolAccess.model.AccessHistory;
import com.example.schoolAccess.repository.AccessHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccessHistoryService {
    private final AccessHistoryRepository accessHistoryRepository;


    public List<AccessHistory> getHistoryByUserId(int userId) {
        return accessHistoryRepository.findAllByUserId(userId);
    }


    public AccessHistory create(AccessHistory accessHistory) {
        return accessHistoryRepository.save(accessHistory);
    }
}
