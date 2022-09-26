package com.example.dental_db.dentalrecords.service;

import com.example.dental_db.dentalrecords.entity.Dentist;
import com.example.dental_db.dentalrecords.entity.Patient;

import java.util.List;

public interface DentistService {
    List<Dentist> getDentists();

    Dentist getDentist(Long id);

    Dentist saveDentist(Dentist dentist);

    void updateDentist(Long id, Dentist dentist);

    void deleteDentist(Long id);

    //Utility Helper
    Dentist addDentistToPatient(Long dentistId, Long patientId);

    void deletePatientFromDentist(Long dentistId, Long patientId);
}
