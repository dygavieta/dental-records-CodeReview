package com.example.dental_db.dentalrecords.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
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

}
