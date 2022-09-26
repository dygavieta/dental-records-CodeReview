package com.example.dental_db.dentalrecords.controller;

import com.example.dental_db.dentalrecords.entity.ContactInfo;
import com.example.dental_db.dentalrecords.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@CrossOrigin
public class ContactInfoController {
    @Autowired
    ContactService service;

    //SHOW CONTACT INFO
    @GetMapping(value = "/patients/{patientId}/contact")
    public ResponseEntity<ContactInfo> showContactInfo(@PathVariable Long patientId) {
        return ResponseEntity.ok().body(service.getContactInfo(patientId));
    }

    //ADD
    @PostMapping(value = "/patients/{patientId}/contact")
    public ResponseEntity<Object> addContactInfo(@PathVariable Long patientId, @RequestBody ContactInfo contactInfo) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/patients/" + patientId + "/contact").toUriString());
        return ResponseEntity.created(uri).body(service.saveContactInfo(patientId, contactInfo));
    }

    //UPDATE CONTACT INFO
    @PutMapping(value = "/patients/{patientId}/contact")
    public ResponseEntity<Object> updateContactInfo(@PathVariable Long patientId, @RequestBody ContactInfo contactInfo) {
        service.updateContactInfo(patientId, contactInfo);
        return ResponseEntity.noContent().build();
    }

    //DELETE CONTACT INFO
    @DeleteMapping(value = "/patients/{patientId}/contact")
    public ResponseEntity<Object> deleteContactInfo(@PathVariable Long patientId) {
        service.deleteContactInfo(patientId);
        return ResponseEntity.noContent().build();
    }

}
