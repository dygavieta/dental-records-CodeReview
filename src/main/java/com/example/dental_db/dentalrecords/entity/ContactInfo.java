package com.example.dental_db.dentalrecords.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_contacts")
public class ContactInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(unique = true)
    private long contactId;
    private String region;
    private String city;
    private String baranggay;
    @Column(nullable = false)
    private String mobileNumber;
    private String emailAddress;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "patient_id", referencedColumnName = "patientId", nullable = false)
    private Patient patient;

    public ContactInfo(String region, String city, String baranggay, String mobileNumber, String emailAddress) {
        this.region = region;
        this.city = city;
        this.baranggay = baranggay;
        this.mobileNumber = mobileNumber;
        this.emailAddress = emailAddress;
    }

    public ContactInfo() {

    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBaranggay() {
        return baranggay;
    }

    public void setBaranggay(String baranggay) {
        this.baranggay = baranggay;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public String toString() {
        return "ContactInfo{" +
                "contactId=" + contactId +
                ", region='" + region + '\'' +
                ", city='" + city + '\'' +
                ", baranggay='" + baranggay + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", emailAddress='" + emailAddress + '}';

    }
}
