package com.example.dental_db.dentalrecords.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_dentists")
public class Dentist {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(unique = true)
    private long dentistId;

    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String specialty;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "dentist_patient",
            joinColumns = @JoinColumn(name = "dentist_id", referencedColumnName = "dentistId"),
            inverseJoinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "patientId"))
    private List<Patient> patients = new ArrayList<>();


    public Dentist(String firstName, String lastName, String specialty) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialty = specialty;
    }

    public Dentist() {
    }

    public long getDentistId() {
        return dentistId;
    }

    public void setDentistId(long dentistId) {
        this.dentistId = dentistId;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
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

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    @Override
    public String toString() {
        return "Dentist{" +
                "dentistId=" + dentistId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", specialty='" + specialty + '\'' +
                ", patients=" + patients +
                '}';
    }
}
