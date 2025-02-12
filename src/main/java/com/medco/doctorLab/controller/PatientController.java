package com.medco.doctorLab.controller;

import java.util.List;

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

	private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService= patientService;
    }
    
    
   	@PostMapping(value="/addpatient")
   	public ResponseEntity<Patient>addpatient(@RequestBody Patient patient){
   		Patient patientadded = patientService.addPatient(patient);
   		return new ResponseEntity<Patient>(HttpStatus.CREATED);
   	}
   

   	@DeleteMapping(value="/delete/{id}")
   	public ResponseEntity<Patient>deletePatient(@PathVariable Long id){
   		boolean patientdeleted=patientService.deletePatient(id);
   		return new ResponseEntity<Patient>(HttpStatus.NOT_FOUND);
   	}

   	//edit patient details
   	@PutMapping(value="/editdetails/{id}")
   	public ResponseEntity<Patient>editPatient(@PathVariable Long id,@RequestBody Patient updatedPatient){
   		Patient editpatient =patientService.editPatient(id, updatedPatient);
   	 return ResponseEntity.ok(editpatient);
   		
   	}
   	
   	//list the patient details
   	@GetMapping(value="/listOfPatients")
   	public ResponseEntity<List<Patient>> getAllPatients(){
   		List<Patient>listOfPatients =patientService.getAllPatients();
   		return  ResponseEntity.ok(listOfPatients );
   		
   		
   	}
   	  
   	//list of all doctors
   	@GetMapping(value="allDoctors")
   	public ResponseEntity<List<Doctor>>getAllDoctors(){
   		List<Doctor>listOfDoctors=patientService.getAllDoctors();
   		return ResponseEntity.ok(listOfDoctors );
   		
   		
   	}
   	
  //pass patient id
//list of doctors should come while fetching this
//    @GetMapping("/cancelled/{PatientId}")
//    public ResponseEntity<List<Doctor>> getCancelledAppointments(@PathVariable Long PatientId) {
//        List<Doctor> cancelledAppointments = patientService.getCancelledAppointment(PatientId);
//        return ResponseEntity.ok(cancelledAppointments);
//    }
    
//   	@GetMapping(value="/upcoming/{PatientId}")
//   	public ResponseEntity<List<Doctor>>getUpcomingAppoitment(@PathVariable Long PatientId){
//   		List<Doctor> upcomingAppoitment=patientService.getUpcomingAppoitment(PatientId);
//   		return ResponseEntity.ok(upcomingAppoitment);
//   	}
//   	
//  
//  	 @GetMapping("/completed/{PatientId}")
//     public ResponseEntity<List<Doctor>> getCompletedAppointments(@PathVariable Long PatientId) {
//         List<Doctor> completedAppointments = patientService.getCompletedAppointment(PatientId);
//         return ResponseEntity.ok(completedAppointments);
//     }
}
