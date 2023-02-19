package com.example.schoolAccess.repository;

import com.example.schoolAccess.model.ContactDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactDetailRepository extends JpaRepository<ContactDetail, Integer> {
}
