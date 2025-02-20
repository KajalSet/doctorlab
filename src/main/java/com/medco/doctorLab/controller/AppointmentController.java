package com.medco.doctorLab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medco.doctorLab.models.Doctor;
import com.medco.doctorLab.service.AppointmentService;



@RestController
@RequestMapping(value="/api/appointment")
public class AppointmentController {
	
	
	@Autowired
	private AppointmentService appointmentService;
   	
  //pass patient id
//list of doctors should come while fetching this
  
   @GetMapping("/cancelled/{patientId}") 
   public ResponseEntity<List<Doctor>> getCancelledAppointments(@PathVariable Long patientId) {
       List<Doctor> cancelledDoctors = appointmentService.getCancelledAppointment(patientId);
       return ResponseEntity.ok(cancelledDoctors);
       
   }

   @GetMapping("/upcoming/{patientId}")
   public ResponseEntity<List<Doctor>> getUpcomingAppointments(@PathVariable Long patientId) {
       List<Doctor> upcomingDoctors =appointmentService.getUpcomingAppointments(patientId);
       return ResponseEntity.ok(upcomingDoctors);
   }

   @GetMapping("/completed/{patientId}")
   public ResponseEntity<List<Doctor>> getCompletedAppointments(@PathVariable Long patientId) {
       List<Doctor> completedDoctors = appointmentService.getCompletedAppointments(patientId);
       return ResponseEntity.ok(completedDoctors);
   }
	

}
