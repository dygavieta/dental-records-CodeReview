package com.example.dental_db.dentalrecords.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "tbl_contacts")
public class ContactInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(unique = true)
    private long contactId;
    private String address;
    @Column(nullable = false)
    private String mobileNumber;
    private String emailAddress;

    @Getter(AccessLevel.NONE)
    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "patientId", nullable = false)
    private Patient patient;

}
