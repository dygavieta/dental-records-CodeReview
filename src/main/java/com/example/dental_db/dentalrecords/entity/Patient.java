package com.example.dental_db.dentalrecords.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
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

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<ContactInfo> contactInfo;

    @OneToOne(mappedBy = "patient", cascade = CascadeType.ALL)
    private DentalHistory dentalHistory;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Treatment> treatments = new ArrayList<>();

    @Getter(value= AccessLevel.NONE)
    @ManyToMany(mappedBy = "patients", fetch = FetchType.LAZY)
    private List<Dentist> dentists = new ArrayList<>();


}
