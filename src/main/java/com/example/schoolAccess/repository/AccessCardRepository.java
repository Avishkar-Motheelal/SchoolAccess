package com.example.schoolAccess.repository;

import com.example.schoolAccess.model.AccessCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AccessCardRepository extends JpaRepository<AccessCard, UUID> {
    List<AccessCard> findByUserUserId(int userId);
}
