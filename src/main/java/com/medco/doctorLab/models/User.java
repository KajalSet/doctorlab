package com.medco.doctorLab.models;


import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(unique = true, nullable = false)
	    private String mobile;

	    private String name;
	    private String role;

    
    public User() {
    }

    public User(String mobile, String role) {
        this.mobile = mobile;
        this.role = role;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(Long id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
