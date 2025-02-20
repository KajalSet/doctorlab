package com.medco.doctorLab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medco.doctorLab.models.Doctor;
import com.medco.doctorLab.models.Patient;
import com.medco.doctorLab.service.PatientService;


@RestController
@RequestMapping("/api/home")
public class PatientController {
	
	
//    private final PatientService patientService;

    @Autowired
    PatientService patientService;

    @PostMapping(value="/addpatient")
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient) {
        return ResponseEntity.status(HttpStatus.CREATED).body(patientService.addPatient(patient));
    }

    @GetMapping(value="/getall")
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatient(@PathVariable Long id) {
        Patient patient = patientService.getPatientById(id);
        return ResponseEntity.ok(patient);
    }
    
    @DeleteMapping(value="/delete/{id}")
   	public ResponseEntity<Patient>deletePatient(@PathVariable Long id){
   		boolean patientdeleted=patientService.deletePatient(id);
   		return new ResponseEntity<Patient>(HttpStatus.OK);
   	}
    
    
    
  //edit patient details
   	@PutMapping(value="/editdetails/{id}")
   	public ResponseEntity<Patient>editPatient(@PathVariable Long id,@RequestBody Patient updatedPatient){
   		Patient editpatient =patientService.editPatient(id, updatedPatient);
   	 return ResponseEntity.ok(editpatient);
   		
   	}
   	
   	@PostMapping("/add")
    public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor) {
        Doctor savedDoctor = patientService.addDoctor(doctor);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDoctor);
    }
   	
  //list of all doctors
  	@GetMapping(value="/alldoctors")
  	public ResponseEntity<List<Doctor>>getAllDoctors(){
  		List<Doctor>listOfDoctors=patientService.getAllDoctors();
  		return ResponseEntity.ok(listOfDoctors );
  			
  	}
}