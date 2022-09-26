package com.example.dental_db.dentalrecords.controller;


import com.example.dental_db.dentalrecords.entity.Patient;
import com.example.dental_db.dentalrecords.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
public class PatientController {

    @Autowired
    PatientService service;

    //HOME
    @GetMapping(value = "/")
    public String showHome() {
        return "<h1> Welcome to Dental-API </h1>";
    }

    //GET PATIENTS
    @GetMapping(value = "/patients")
    public ResponseEntity<List<Patient>> showAllPatients() {
        return ResponseEntity.ok().body(service.getPatients());
    }

    //Get PATIENTS BY AGE
    @GetMapping(value = "/patients/")
    public ResponseEntity<List<Patient>> showAllPatientsByAge(@RequestParam Integer age) {
        return ResponseEntity.ok().body(service.getPatientsByAge(age));
    }

    //GET A PATIENT
    @GetMapping(value = "/patients/{patientID}")
    public ResponseEntity<Patient> showPatient(@PathVariable Long patientID) {
        return ResponseEntity.ok().body(service.getPatient(patientID));
    }


    //ADD PATIENT
    @PostMapping(value = "/patients")
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient) {
        Patient savedPatient = service.savePatient(patient);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/patients/" + savedPatient.getPatientId()).toUriString());
        return ResponseEntity.created(uri).body(service.savePatient(patient));
    }

    //UPDATE PATIENT
    @PutMapping(value = "/patients/{patientID}")
    public ResponseEntity<Object> showPatient(@PathVariable Long patientID, @RequestBody Patient patient) {
        service.updatePatient(patientID, patient);
        return ResponseEntity.noContent().build();
    }

    //DELETE ALL PATIENT
    @DeleteMapping(value = "/patients/deleteAll")
    public ResponseEntity<Object> deleteAllPatients() {
        service.deleteAllPatients();
        return ResponseEntity.noContent().build();
    }

    //DELETE A PATIENT
    @DeleteMapping(value = "/patients/{patientID}")
    public ResponseEntity<Object> deletePatient(@PathVariable Long patientID) {
        service.deletePatient(patientID);
        return ResponseEntity.noContent().build();
    }


}
