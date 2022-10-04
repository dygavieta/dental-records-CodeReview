package com.example.dental_db.dentalrecords.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Data
@Table(name = "tbl_treatments")
public class Treatment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(unique = true)
    private Long treatmentId;

    @Column(name = "date_of_treatment", nullable = false)
    @JsonFormat(pattern = "MM-dd-yyyy")
    private LocalDate date;

    @Column(nullable = false)
    private int toothNumber;

    @Column(nullable = false)
    private String description;

    @ManyToOne(optional = false)
    @JoinColumn(name = "patient_id", referencedColumnName = "patientId", nullable = false)
    private Patient patient;


}
