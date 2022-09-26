package com.example.dental_db.dentalrecords.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
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

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "patient_id", referencedColumnName = "patientId", nullable = false)
    private Patient patient;

    public Treatment(LocalDate date, int toothNumber, String description) {
        this.date = date;
        this.toothNumber = toothNumber;
        this.description = description;
    }

    public Treatment() {

    }

    public Long getTreatmentId() {
        return treatmentId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getToothNumber() {
        return toothNumber;
    }

    public void setToothNumber(int toothNumber) {
        this.toothNumber = toothNumber;
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
        return "Treatment{" +
                "treatmentId=" + treatmentId +
                ", date=" + date +
                ", toothNumber=" + toothNumber +
                ", description='" + description + '\'' +
                '}';
    }
}
