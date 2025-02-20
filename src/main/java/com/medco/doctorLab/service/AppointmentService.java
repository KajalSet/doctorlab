package com.medco.doctorLab.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.medco.doctorLab.models.Appointment;
import com.medco.doctorLab.models.Doctor;
import com.medco.doctorLab.repo.AppointmentRepository;


public class AppointmentService {
	@Autowired
	private AppointmentRepository appointmentRepository;
	

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
