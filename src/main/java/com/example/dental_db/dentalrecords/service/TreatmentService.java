package com.example.dental_db.dentalrecords.service;

import com.example.dental_db.dentalrecords.entity.Treatment;

import java.util.List;

public interface TreatmentService {
    List<Treatment> getTreatments(Long patientId);

    Treatment getTreatment(Long patientId, Long treatmentId);

    Treatment saveTreatment(Long patientId, Treatment treatment);

    void updateTreatment(Long patientId, Long treatmentId, Treatment treatment);

    void deleteTreatment(Long patientId, Long treatmentId);

    void deleteAllTreatments(Long patientId);

}
