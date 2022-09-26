package com.example.dental_db.dentalrecords.service.implementation;

import com.example.dental_db.dentalrecords.entity.Patient;
import com.example.dental_db.dentalrecords.entity.Treatment;
import com.example.dental_db.dentalrecords.repository.TreatmentRepository;
import com.example.dental_db.dentalrecords.service.PatientService;
import com.example.dental_db.dentalrecords.service.TreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class TreatmentServiceImpl implements TreatmentService {

    @Autowired
    private PatientService patientService;
    @Autowired
    private TreatmentRepository treatmentRepository;

    @Override
    public List<Treatment> getTreatments(Long patientId) {
        Patient patient = patientService.getPatient(patientId);
        if (patient == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return patient.getTreatments();
    }

    @Override
    public Treatment getTreatment(Long patientId, Long treatmentId) {
        List<Treatment> treatments = getTreatments(patientId);
        Optional<Treatment> temp_treatment = treatments.stream().filter(e -> (e.getTreatmentId().equals(treatmentId))).findFirst();
        Treatment treatment = temp_treatment.orElse(null);
        if (treatment == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Treatment Not Found");
        return treatment;
    }

    @Override
    public Treatment saveTreatment(Long patientId, Treatment treatment) {
        Patient patient = patientService.getPatient(patientId);
        patient.getTreatments().add(treatment);
        //Ownership
        treatment.setPatient(patient);
        return treatmentRepository.save(treatment);
    }

    @Override
    public void updateTreatment(Long patientId, Long treatmentId, Treatment treatment) {
        Treatment dbTreatment = getTreatment(patientId, treatmentId);
        dbTreatment.setDate(treatment.getDate());
        dbTreatment.setToothNumber(treatment.getToothNumber());
        dbTreatment.setDescription(treatment.getDescription());
        treatmentRepository.save(dbTreatment);
    }

    @Override
    public void deleteTreatment(Long patientId, Long treatmentId) {
        Patient patient = patientService.getPatient(patientId);
        Treatment dbTreatment = getTreatment(patientId, treatmentId);
        //Abandon link
        patient.setTreatments(null);
        treatmentRepository.delete(dbTreatment);
    }

    @Override
    public void deleteAllTreatments(Long patientId) {
        treatmentRepository.deleteAllInBatch();
    } //all
}
