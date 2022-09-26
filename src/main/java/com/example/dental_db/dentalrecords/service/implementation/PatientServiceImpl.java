package com.example.dental_db.dentalrecords.service.implementation;

import com.example.dental_db.dentalrecords.entity.ContactInfo;
import com.example.dental_db.dentalrecords.entity.Dentist;
import com.example.dental_db.dentalrecords.entity.Patient;
import com.example.dental_db.dentalrecords.repository.DentistRepository;
import com.example.dental_db.dentalrecords.repository.PatientRepository;
import com.example.dental_db.dentalrecords.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;


    @Override
    public List<Patient> getPatients() {
        return patientRepository.findAll();
    }

    public List<Patient> getPatientsByAge(Integer id) {
        return patientRepository.findPatientByAgeEquals(id);
    }

    @Override
    public Patient getPatient(Long id) {
        Optional<Patient> temp_patient = patientRepository.findById(id);
        Patient patient = temp_patient.orElse(null);
        if (patient == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient Not Found");
        return (patient);
    }

    @Override
    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public void updatePatient(Long id, Patient patient) {
        Patient dbPatient = getPatient(id);
        if (dbPatient == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        //UPDATE SET QUERY
        dbPatient.setFirstName(patient.getFirstName());
        dbPatient.setLastName(patient.getLastName());
        dbPatient.setMiddleInitial(patient.getMiddleInitial());
        dbPatient.setSex(patient.getSex());
        dbPatient.setAge(patient.getAge());
        dbPatient.setNationality(patient.getNationality());
        dbPatient.setOccupation(patient.getOccupation());
        dbPatient.setDob(patient.getDob());
        patientRepository.save(dbPatient);
    }

    @Override
    public void deletePatient(Long id) {
        Patient patient = getPatient(id);
        patientRepository.delete(patient);
    }

    @Override
    public void deleteAllPatients() {
        patientRepository.deleteAll();
    } // iterator

}
