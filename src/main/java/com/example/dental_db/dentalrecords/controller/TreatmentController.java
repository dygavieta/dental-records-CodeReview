package com.example.dental_db.dentalrecords.controller;

import com.example.dental_db.dentalrecords.entity.Treatment;
import com.example.dental_db.dentalrecords.service.TreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class TreatmentController {

    @Autowired
    TreatmentService service;

    //SHOW TREATMENTS
    @GetMapping(value = "/patients/{patientId}/treatments")
    public ResponseEntity<List<Treatment>> showAllTreatments(@PathVariable Long patientId) {
        return ResponseEntity.ok().body(service.getTreatments(patientId));
    }

    @GetMapping(value = "/patients/{patientId}/treatments/{treatmentId}")
    public ResponseEntity<Treatment> showTreatment(@PathVariable Long patientId, @PathVariable Long treatmentId) {
        return ResponseEntity.ok().body(service.getTreatment(patientId, treatmentId));
    }

    //ADD TREATMENT
    @PostMapping(value = "/patients/{patientId}/treatments")
    public ResponseEntity<Treatment> addTreatment(@PathVariable Long patientId, @RequestBody Treatment treatment) {
        Treatment savedTreatment = service.saveTreatment(patientId, treatment);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/patients/" + patientId + "/treatments/" + savedTreatment.getTreatmentId()).toUriString());
        return ResponseEntity.created(uri).body(savedTreatment);
    }

    //UPDATE TREATMENT
    @PutMapping(value = "/patients/{patientId}/treatments/{treatmentId}")
    public ResponseEntity<Object> updateTreatment(@PathVariable Long patientId, @PathVariable Long treatmentId, @RequestBody Treatment treatment) {
        service.updateTreatment(patientId, treatmentId, treatment);
        return ResponseEntity.noContent().build();
    }

    //DELETE TREATMENT
    @DeleteMapping(value = "/patients/{patientId}/treatments/{treatmentId}")
    public ResponseEntity<Object> deleteTreatment(@PathVariable Long patientId, @PathVariable Long treatmentId) {
        service.deleteTreatment(patientId, treatmentId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/patients/{patientId}/treatments/deleteAll")
    public ResponseEntity<Object> deleteAllTreatments(@PathVariable Long patientId) {
        service.deleteAllTreatments(patientId);
        return ResponseEntity.noContent().build();
    }


}
