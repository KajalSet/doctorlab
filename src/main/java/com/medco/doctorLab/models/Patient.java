package com.medco.doctorLab.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Data
@Entity
@Table(name = "patient")
public class Patient {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String bookingFor;
	private String name;
    private int age;
    private String gender;
    private String writeProblem;
   
    
    @OneToMany(mappedBy = "patients", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference  // Helps manage serialization
    private List<Doctor> doctors;
    
    
	public String getBookingFor() {
		return bookingFor;
	}
	public void setBookingFor(String bookingFor) {
		this.bookingFor = bookingFor;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getWriteProblem() {
		return writeProblem;
	}
	public void setWriteProblem(String writeProblem) {
		this.writeProblem = writeProblem;
	}
	public Patient(Long id, String bookingFor, String name, int age, String gender, String writeProblem) {
		super();
		this.id = id;
		this.bookingFor = bookingFor;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.writeProblem = writeProblem;
	}
	public Patient() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}
