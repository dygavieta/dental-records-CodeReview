package com.example.dental_db.dentalrecords.service;

import com.example.dental_db.dentalrecords.entity.ContactInfo;
import com.example.dental_db.dentalrecords.entity.Patient;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ContactService {
    ContactInfo getContactInfo(Long id);

    ContactInfo saveContactInfo(Long patientID, ContactInfo contactInfo);

    void updateContactInfo(Long patientID, ContactInfo contactInfo);

    void deleteContactInfo(Long patientID);

}
