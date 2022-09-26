package com.example.dental_db.dentalrecords.repository;

import com.example.dental_db.dentalrecords.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findPatientByAgeEquals(Integer age);
}
