package com.example.dental_db.dentalrecords.service;

import com.example.dental_db.dentalrecords.entity.Patient;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PatientService {
    List<Patient> getPatients();

    List<Patient> getPatientsByAge(Integer id);

    Patient getPatient(Long id);

    Patient savePatient(Patient patient);

    void updatePatient(Long id, Patient patient);

    void deletePatient(Long id);

    void deleteAllPatients();
}
