package com.medco.doctorLab.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medco.doctorLab.models.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long>{
	
	List<Appointment> findByPatientIdAndStatus(Long patientId, String string);

}
