package com.example.dental_db.dentalrecords.repository;

import com.example.dental_db.dentalrecords.entity.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreatmentRepository extends JpaRepository<Treatment, Long> {
}
