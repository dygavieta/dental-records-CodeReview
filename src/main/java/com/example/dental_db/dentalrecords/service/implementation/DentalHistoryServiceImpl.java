package com.example.dental_db.dentalrecords.service.implementation;

import com.example.dental_db.dentalrecords.entity.ContactInfo;
import com.example.dental_db.dentalrecords.entity.DentalHistory;
import com.example.dental_db.dentalrecords.entity.Patient;
import com.example.dental_db.dentalrecords.repository.DentalHistoryRepository;
import com.example.dental_db.dentalrecords.service.DentalHistoryService;
import com.example.dental_db.dentalrecords.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DentalHistoryServiceImpl implements DentalHistoryService {

    @Autowired
    private PatientService patientService;

    @Autowired
    private DentalHistoryRepository dentalHistoryRepository;


    @Override
    public DentalHistory getDentalHistory(Long patientId) {
        Patient patient = patientService.getPatient(patientId);
        //return patient.getDentalHistory();
        return dentalHistoryRepository.findDentalHistoryByPatientPatientId(patientId);
    }

    @Override
    public DentalHistory saveDentalHistory(Long patientId, DentalHistory dentalHistory) {
        Patient patient = patientService.getPatient(patientId);
        dentalHistory.setPatient(patient);
        return dentalHistoryRepository.save(dentalHistory);
    }


    @Override
    public void updateDentalHistory(Long patientId, DentalHistory dentalHistory) {
        DentalHistory dbDentalHistory = getDentalHistory(patientId);
        dbDentalHistory.setDate(dentalHistory.getDate());
        dbDentalHistory.setDescription(dentalHistory.getDescription());
        dentalHistoryRepository.save(dbDentalHistory);
    }

    @Override
    public void deleteDentalHistory(Long patientId) {
        Patient patient = patientService.getPatient(patientId);
        DentalHistory dbDentalHistory = getDentalHistory(patientId);
        //Abandon link
        patient.setDentalHistory(null);
        dentalHistoryRepository.delete(dbDentalHistory);
    }
}
