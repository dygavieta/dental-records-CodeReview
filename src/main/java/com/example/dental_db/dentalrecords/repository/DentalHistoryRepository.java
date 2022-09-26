package com.example.dental_db.dentalrecords.repository;

import com.example.dental_db.dentalrecords.entity.DentalHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DentalHistoryRepository extends JpaRepository<DentalHistory, Long> {
    DentalHistory findDentalHistoryByPatientPatientId(long patientId);
};
