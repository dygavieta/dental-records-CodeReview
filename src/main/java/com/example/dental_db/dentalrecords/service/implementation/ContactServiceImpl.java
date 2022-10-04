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

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactInfoRepository contactInfoRepository;
    @Autowired
    private PatientService patientService;

    @Override
    public List<ContactInfo> getAllContactInfo(Long patientId) {
        Patient patient = patientService.getPatient(patientId);
        return  patient.getContactInfo();
    }
    @Override
    public ContactInfo getContactInfo(Long patientID, Long contactId) {
        return contactInfoRepository.findContactInfoByContactId(contactId);
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
        ContactInfo dbContactInfo = getContactInfo(patientID, contactInfo.getContactId());
        if (dbContactInfo == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient's Contact Not Found");
        dbContactInfo.setAddress(contactInfo.getAddress());
        dbContactInfo.setEmailAddress(contactInfo.getEmailAddress());
        dbContactInfo.setMobileNumber(contactInfo.getMobileNumber());
        contactInfoRepository.save(dbContactInfo);
    }

    @Override
    public void deleteContactInfo(Long patientID, Long contactId) {
        Patient patient = patientService.getPatient(patientID);
        ContactInfo dbContactInfo = getContactInfo(patientID, contactId);
        if (dbContactInfo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient's Contact Not Found");
        //Abandon link
        patient.setContactInfo(null);
        contactInfoRepository.delete(dbContactInfo);
    }


}
