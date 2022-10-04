package com.example.dental_db.dentalrecords.controller;

import com.example.dental_db.dentalrecords.entity.ContactInfo;
import com.example.dental_db.dentalrecords.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/patients")
public class ContactInfoController {
    @Autowired
    ContactService service;
    //SHOW ALL CONTACT INFO
    @GetMapping(value = "{patientId}/contacts")
    public ResponseEntity<List<ContactInfo>> showContacts(@PathVariable Long patientId){
        return ResponseEntity.ok().body(service.getAllContactInfo(patientId));
    }
    //SHOW CONTACT INFO
    @GetMapping(value = "/{patientId}/contacts/{contactId}")
    public ResponseEntity<ContactInfo> showContact(@PathVariable Long patientId, @PathVariable Long contactId) {
        return ResponseEntity.ok().body(service.getContactInfo(patientId, contactId));
    }

    //ADD
    @PostMapping(value = "/{patientId}/contacts")
    public ResponseEntity<Object> addContactInfo(@PathVariable Long patientId, @RequestBody ContactInfo contactInfo) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/patients/" + patientId + "/contact").toUriString());
        return ResponseEntity.created(uri).body(service.saveContactInfo(patientId, contactInfo));
    }

    //UPDATE CONTACT INFO
    @PutMapping(value = "/{patientId}/contacts")
    public ResponseEntity<Object> updateContactInfo(@PathVariable Long patientId, @RequestBody ContactInfo contactInfo) {
        service.updateContactInfo(patientId, contactInfo);
        return ResponseEntity.noContent().build();
    }

    //DELETE CONTACT INFO
    @DeleteMapping(value = "/{patientId}/contacts/{contactId}")
    public ResponseEntity<Object> deleteContactInfo(@PathVariable Long patientId, @PathVariable Long contactId) {
        service.deleteContactInfo(patientId, contactId);
        return ResponseEntity.noContent().build();
    }

}
