package com.example.dental_db.dentalrecords.repository;

import com.example.dental_db.dentalrecords.entity.ContactInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactInfoRepository extends JpaRepository<ContactInfo, Long> {
    ContactInfo findContactInfoByContactId(Long contactId);
}
