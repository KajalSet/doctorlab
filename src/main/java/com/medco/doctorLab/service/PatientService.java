package com.medco.doctorLab.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medco.doctorLab.Exception.ResourceNotFoundException;
import com.medco.doctorLab.models.Appointment;
import com.medco.doctorLab.models.Doctor;
import com.medco.doctorLab.models.Patient;
import com.medco.doctorLab.repo.AppointmentRepository;
import com.medco.doctorLab.repo.DoctorRepository;
import com.medco.doctorLab.repo.PatientRepository;



@Transactional
@Service
public class PatientService {
//    private final PatientRepository patientRepository;

    @Autowired
    PatientRepository patientRepository;
//    public PatientService(PatientRepository patientRepository) {
//        this.patientRepository = patientRepository;
//    }

    @Autowired
    private DoctorRepository doctorRepository;
    
    public Patient addPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }
    
	
	public Patient getPatientById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
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
	existingPatient.setReason(updatedPatient.getReason());
	
	return patientRepository.save(existingPatient);
}


public Doctor addDoctor(Doctor doctor) {
    return doctorRepository.save(doctor);
}

public List<Doctor> getAllDoctors() {
	
	return doctorRepository.findAll();
}

	
}
