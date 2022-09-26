package com.example.dental_db.dentalrecords.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
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

    public DentalHistory(LocalDate date, String description) {
        this.date = date;
        this.description = description;
    }

    public DentalHistory() {

    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public String toString() {
        return "DentalHistory{" +
                "dentalHistoryId=" + dentalHistoryId +
                ", date=" + date +
                ", description='" + description + '\'' +
                '}';
    }
}
