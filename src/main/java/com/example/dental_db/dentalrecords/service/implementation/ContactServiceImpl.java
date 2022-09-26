package com.example.dental_db.dentalrecords.service.implementation;

import com.example.dental_db.dentalrecords.entity.ContactInfo;
import com.example.dental_db.dentalrecords.entity.Patient;
import com.example.dental_db.dentalrecords.repository.ContactInfoRepository;
import com.example.dental_db.dentalrecords.service.ContactService;
import com.example.dental_db.dentalrecords.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactInfoRepository contactInfoRepository;
    @Autowired
    private PatientService patientService;

    public void checkPatient(Long patientId) {
        patientService.getPatient(patientId);
    }

    @Override
    public ContactInfo getContactInfo(Long patientID) {
        checkPatient(patientID);
        return contactInfoRepository.findContactInfoByPatientPatientId(patientID);
    }

    @Override
    public ContactInfo saveContactInfo(Long patientID, ContactInfo contactInfo) {
        Patient patient = patientService.getPatient(patientID);
        //set parent
        contactInfo.setPatient(patient);
        return contactInfoRepository.save(contactInfo);
    }

    @Override
    public void updateContactInfo(Long patientID, ContactInfo contactInfo) {
        ContactInfo dbContactInfo = getContactInfo(patientID);
        if (dbContactInfo == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient's Contact Not Found");
        dbContactInfo.setCity(contactInfo.getCity());
        dbContactInfo.setBaranggay(contactInfo.getBaranggay());
        dbContactInfo.setRegion(contactInfo.getRegion());
        dbContactInfo.setEmailAddress(contactInfo.getEmailAddress());
        dbContactInfo.setMobileNumber(contactInfo.getMobileNumber());
        contactInfoRepository.save(dbContactInfo);
    }

    @Override
    public void deleteContactInfo(Long patientID) {
        Patient patient = patientService.getPatient(patientID);
        ContactInfo contactInfo = patient.getContactInfo();
        if (contactInfo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient's Contact Not Found");
        //Abandon link
        patient.setContactInfo(null);
        contactInfoRepository.delete(contactInfo);
    }
}
