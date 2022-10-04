package com.example.dental_db.dentalrecords.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Data
@Table(name = "tbl_dentalHistories")
public class DentalHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(unique = true)
    private long dentalHistoryId;

    @JsonFormat(pattern = "MM-dd-yyyy")
    private LocalDate date;

    @Column(nullable = false)
    private String description;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "patient_id", referencedColumnName = "patientId", nullable = false)
    private Patient patient;


}
