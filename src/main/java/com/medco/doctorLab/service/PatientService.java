package com.medco.doctorLab.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medco.doctorLab.Exception.ResourceNotFoundException;
import com.medco.doctorLab.models.Appointment;
import com.medco.doctorLab.models.Doctor;
import com.medco.doctorLab.models.Patient;
import com.medco.doctorLab.repo.AppointmentRepository;
import com.medco.doctorLab.repo.DoctorRepository;
import com.medco.doctorLab.repo.PatientRepository;


@Service
public class PatientService {
	
	 @Autowired
	 private PatientRepository patientRepository;
	 @Autowired
	 private DoctorRepository doctorRepository;
	 
	 @Autowired
	 private AppointmentRepository appointmentRepository;

	 
	 
	public Patient addPatient(Patient patient) {
		
	    return patientRepository.save(patient); 
	}

	public boolean deletePatient(Long id) {
		
		Optional<Patient> patient=patientRepository.findById(id);
		if(patient.isPresent()) {
			patientRepository.deleteById(id);
			return true;
		}
		return false;	
		
	}

	public Patient editPatient(Long id, Patient updatedPatient) {
		
		Patient existingPatient=patientRepository.findById(id)
		.orElseThrow(()->new ResourceNotFoundException("Patient not found with this id: "+ id));
		
		existingPatient.setName(updatedPatient.getName());
		existingPatient.setAge(updatedPatient.getAge());
		existingPatient.setGender(updatedPatient.getGender());
		existingPatient.setBookingFor(updatedPatient.getBookingFor());
		
		return patientRepository.save(existingPatient);
	}

	public List<Patient> getAllPatients() {
		
		  return patientRepository.findAll();
	}

	public List<Doctor> getAllDoctors() {
		
		return doctorRepository.findAll();
		
	}

	
	//appointment history -upcoming completed cancelled
	
     	
	public List<Doctor> getCancelledAppointment(Long patientId) {
	    List<Appointment> cancelledAppointments = appointmentRepository.findByPatientIdAndStatus(patientId, "CANCELLED");

	    List<Doctor> cancelledDoctors = new ArrayList<>();
	    for (Appointment appointment : cancelledAppointments) {
	        cancelledDoctors.add(appointment.getDoctor());
	    }
	    return cancelledDoctors;
	}
	

	public List<Doctor> getUpcomingAppointments(Long patientId) {
	    List<Appointment> upcomingAppointments = appointmentRepository.findByPatientIdAndStatus(patientId, "UPCOMING");

	    List<Doctor> upcomingDoctors = new ArrayList<>();
	    for (Appointment appointment : upcomingAppointments) {
	        upcomingDoctors.add(appointment.getDoctor());
	    }
	    return upcomingDoctors;
	}
	
	public List<Doctor> getCompletedAppointments(Long patientId) {
	    List<Appointment> completedAppointments = appointmentRepository.findByPatientIdAndStatus(patientId, "COMPLETED");

	    List<Doctor> completedDoctors = new ArrayList<>();
	    for (Appointment appointment : completedAppointments) {
	        completedDoctors.add(appointment.getDoctor());
	    }
	    return completedDoctors;
	}


	

	
}
