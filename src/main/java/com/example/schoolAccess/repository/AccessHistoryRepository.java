package com.example.schoolAccess.repository;

import com.example.schoolAccess.model.AccessHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccessHistoryRepository extends JpaRepository<AccessHistory, Integer> {
    @Query(value = """
        SELECT ah.* FROM ACCESSHISTORY ah
        INNER JOIN ACCESSCARDS ac ON ah.AccessCardID = ac.AccessCardID
        INNER JOIN USERS u ON ac.UserID = u.UserID
        WHERE u.UserID = ?1
        """,
        nativeQuery = true
    )
    List<AccessHistory> findAllByUserId(int id);
}
