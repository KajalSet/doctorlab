package com.medco.doctorLab.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medco.doctorLab.models.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long>{

	List<Doctor> findAll();

	List<Doctor> findAllById(Long patientId);



}
