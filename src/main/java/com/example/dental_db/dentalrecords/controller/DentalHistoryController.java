package com.example.dental_db.dentalrecords.controller;

import com.example.dental_db.dentalrecords.entity.DentalHistory;
import com.example.dental_db.dentalrecords.service.DentalHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class DentalHistoryController {

    @Autowired
    DentalHistoryService service;

    //SHOW
    @GetMapping(value = "/patients/{patientId}/dental-history")
    public DentalHistory showDentalHistory(@PathVariable Long patientId) {
        return service.getDentalHistory(patientId);
    }

    //ADD
    @PostMapping(value = "/patients/{patientId}/dental-history")
    public ResponseEntity<DentalHistory> addDentalHistory(@PathVariable Long patientId, @RequestBody DentalHistory dentalHistory) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/patients/" + patientId + "/dental-history").toUriString());
        return ResponseEntity.created(uri).body(service.saveDentalHistory(patientId, dentalHistory));
    }

    //UPDATE
    @PutMapping(value = "/patients/{patientId}/dental-history")
    public ResponseEntity<Object> updateDentalHistory(@PathVariable Long patientId, @RequestBody DentalHistory dentalHistory) {
        service.updateDentalHistory(patientId, dentalHistory);
        return ResponseEntity.noContent().build();
    }

    //DELETE
    @DeleteMapping(value = "/patients/{patientId}/dental-history")
    public ResponseEntity<Object> deleteDentalHistory(@PathVariable Long patientId) {
        service.deleteDentalHistory(patientId);
        return ResponseEntity.noContent().build();
    }

}
