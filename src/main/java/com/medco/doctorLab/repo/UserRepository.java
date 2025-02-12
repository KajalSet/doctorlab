package com.medco.doctorLab.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medco.doctorLab.models.User;



public interface UserRepository extends JpaRepository<User, Long> {

	

	User findByMobile(String mobileNumber);

	//User findByMobile(String mobileNumber);

	


	
	

}
