package com.example.dental_db.dentalrecords.service;

import com.example.dental_db.dentalrecords.entity.ContactInfo;
import com.example.dental_db.dentalrecords.entity.DentalHistory;

public interface DentalHistoryService {
    DentalHistory getDentalHistory(Long id);

    DentalHistory saveDentalHistory(Long patientID, DentalHistory dentalHistory);

    void updateDentalHistory(Long patientID, DentalHistory dentalHistory);
 
}
