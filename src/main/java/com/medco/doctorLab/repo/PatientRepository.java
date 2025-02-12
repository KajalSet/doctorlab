package com.medco.doctorLab.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medco.doctorLab.models.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>{

}
