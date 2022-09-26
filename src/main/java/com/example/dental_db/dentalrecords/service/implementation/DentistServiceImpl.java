package com.example.dental_db.dentalrecords.service.implementation;


import com.example.dental_db.dentalrecords.entity.Dentist;
import com.example.dental_db.dentalrecords.entity.Patient;
import com.example.dental_db.dentalrecords.repository.DentistRepository;
import com.example.dental_db.dentalrecords.repository.PatientRepository;
import com.example.dental_db.dentalrecords.service.DentistService;
import com.example.dental_db.dentalrecords.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class DentistServiceImpl implements DentistService {
    @Autowired
    private DentistRepository dentistRepository;

    @Autowired
    private PatientService patientService;

    @Override
    public List<Dentist> getDentists() {
        return dentistRepository.findAll();
    }

    @Override
    public Dentist getDentist(Long dentistId) {
        Optional<Dentist> temp_dentist = dentistRepository.findById(dentistId);
        Dentist dentist = temp_dentist.orElse(null);
        if (dentist == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Dentist Not Found");
        return dentist;
    }

    @Override
    public Dentist saveDentist(Dentist dentist) {
        return dentistRepository.save(dentist);
    }

    @Override
    public void updateDentist(Long dentistId, Dentist dentist) {
        Dentist dbDentist = getDentist(dentistId);
        dbDentist.setFirstName(dentist.getFirstName());
        dbDentist.setLastName(dentist.getLastName());
        dbDentist.setSpecialty(dentist.getSpecialty());
        dentistRepository.save(dbDentist);
    }

    @Override
    public void deleteDentist(Long dentistId) {
        Dentist dentist = getDentist(dentistId);
        dentistRepository.delete(dentist);
    }

    @Override
    public Dentist addDentistToPatient(Long dentistId, Long patientId) {
        Dentist dentist = getDentist(dentistId);
        Patient patient = patientService.getPatient(patientId);
        Optional<Patient> dbPatient = dentist.getPatients().stream().filter(p -> p.getPatientId().equals(patientId)).findFirst();
        if (dbPatient.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "PATIENT'S EXIST ALREADY");
        } else {
            dentist.getPatients().add(patient);
            return dentistRepository.save(dentist);
        }

    }

    @Override
    public void deletePatientFromDentist(Long dentistId, Long patientId) {
        Dentist dentist = getDentist(dentistId);
        dentist.getPatients().removeIf(patient -> (patient.getPatientId().equals(patientId)));
        dentistRepository.save(dentist);
    }


}
