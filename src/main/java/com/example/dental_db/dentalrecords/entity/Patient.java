package com.example.dental_db.dentalrecords.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(unique = true)
    private Long patientId;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    private String middleInitial;
    @Column(nullable = false)
    private String sex;
    @Column(nullable = false)
    private int age;
    @Column(nullable = false)
    private String nationality;
    @Column(name = "date_of_birth", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;
    private String occupation;

    @OneToOne(mappedBy = "patient", cascade = CascadeType.ALL)
    private ContactInfo contactInfo;

    @OneToOne(mappedBy = "patient", cascade = CascadeType.ALL)
    private DentalHistory dentalHistory;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Treatment> treatments = new ArrayList<>();

    @ManyToMany(mappedBy = "patients", fetch = FetchType.LAZY)
    private List<Dentist> dentists = new ArrayList<>();

    public Patient(String firstName, String lastName, String middleInitial, String sex, int age, String nationality, LocalDate dob, String occupation) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleInitial = middleInitial;
        this.sex = sex;
        this.age = age;
        this.nationality = nationality;
        this.dob = dob;
        this.occupation = occupation;
    }

    public Patient() {

    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleInitial() {
        return middleInitial;
    }

    public void setMiddleInitial(String middleInitial) {
        this.middleInitial = middleInitial;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    public DentalHistory getDentalHistory() {
        return dentalHistory;
    }

    public void setDentalHistory(DentalHistory dentalHistory) {
        this.dentalHistory = dentalHistory;
    }

    public List<Treatment> getTreatments() {
        return treatments;
    }

    public void setTreatments(List<Treatment> treatments) {
        this.treatments = treatments;
    }

    public void setDentists(List<Dentist> dentists) {
        this.dentists = dentists;
    }


    @Override
    public String toString() {
        return "Patient{" +
                "id=" + patientId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleInitial='" + middleInitial + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", nationality='" + nationality + '\'' +
                ", dob=" + dob +
                ", occupation='" + occupation + '\'' +
                ", contactInfo=" + contactInfo +
                ", dentalHistory=" + dentalHistory +
                ", treatments=" + treatments +
                '}';
    }


}
