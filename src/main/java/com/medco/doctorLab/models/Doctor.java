package com.medco.doctorLab.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.Data;

@Entity
@Table()
public class Doctor {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String department;
	private String about;
	
	
	
	public Doctor() {
		super();
		// TODO Auto-generated constructor stub
	}



	@ManyToOne
	@JoinColumn(name = "patient_id")
	@JsonIgnore // Prevents infinite recursion
	private Patient patient;



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getDepartment() {
		return department;
	}



	public void setDepartment(String department) {
		this.department = department;
	}



	public String getAbout() {
		return about;
	}



	public void setAbout(String about) {
		this.about = about;
	}



	public Patient getPatient() {
		return patient;
	}



	public void setPatient(Patient patient) {
		this.patient = patient;
	}



	public Doctor(Long id, String name, String department, String about, Patient patient) {
		super();
		this.id = id;
		this.name = name;
		this.department = department;
		this.about = about;
		this.patient = patient;
	}
	
	
}
