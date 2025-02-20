package com.medco.doctorLab.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medco.doctorLab.models.User;



public interface UserRepository extends JpaRepository<User, Long> {

	

	User save(User newUser);
	


	
	

}
