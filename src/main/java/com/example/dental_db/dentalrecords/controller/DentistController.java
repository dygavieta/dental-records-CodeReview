package com.example.dental_db.dentalrecords.controller;

import com.example.dental_db.dentalrecords.entity.Dentist;
import com.example.dental_db.dentalrecords.service.DentistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin
public class DentistController {
    @Autowired
    DentistService service;

    //SHOW
    @GetMapping(value = "/dentists")
    public ResponseEntity<List<Dentist>> showAllDentists() {
        return ResponseEntity.ok().body(service.getDentists());
    }

    @GetMapping(value = "/dentists/{dentistId}")
    public Dentist showAllDentists(@PathVariable long dentistId) {
        return service.getDentist(dentistId);
    }

    //ADD
    @PostMapping(value = "/dentists")
    public ResponseEntity<Dentist> addDentist(@RequestBody Dentist dentist) {
        Dentist savedDentist = service.saveDentist(dentist);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/dentists/" + dentist.getDentistId()).toUriString());
        return ResponseEntity.created(uri).body(savedDentist);
    }

    //UPDATE
    @PutMapping(value = "/dentists/{dentistId}")
    public ResponseEntity<Object> updateDentist(@PathVariable Long dentistId, @RequestBody Dentist dentist) {
        service.updateDentist(dentistId, dentist);
        return ResponseEntity.noContent().build();
    }

    //DELETE
    @DeleteMapping(value = "/dentists/{dentistId}")
    public ResponseEntity<Object> deleteDentist(@PathVariable Long dentistId) {
        service.deleteDentist(dentistId);
        return ResponseEntity.noContent().build();
    }

    //ADD PATIENT TO DENTIST
    @PostMapping(value = "/dentists/{dentistId}/patients/{patientId}")
    public ResponseEntity<Dentist> addPatientToDentist(@PathVariable Long dentistId, @PathVariable Long patientId) {
        Dentist savedDentist = service.addDentistToPatient(dentistId, patientId);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/dentists/" + dentistId).toUriString());
        return ResponseEntity.created(uri).body(savedDentist);
    }

    //DELETE PATIENT FROM A DENTIST
    @DeleteMapping(value = "/dentists/{dentistId}/patients/{patientId}")
    public ResponseEntity<Object> deletePatientFromDentist(@PathVariable Long dentistId, @PathVariable Long patientId) {
        service.deletePatientFromDentist(dentistId, patientId);
        return ResponseEntity.noContent().build();
    }

}
