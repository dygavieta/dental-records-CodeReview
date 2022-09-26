package com.example.dental_db.dentalrecords.repository;

import com.example.dental_db.dentalrecords.entity.Dentist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DentistRepository extends JpaRepository<Dentist, Long> {

}
