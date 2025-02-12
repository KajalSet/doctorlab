package com.medco.doctorLab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.medco.deoctorLab.filters.JwtTokenProvider;
import com.medco.doctorLab.models.User;
import com.medco.doctorLab.pojo.request.AuthenticationRequest;
import com.medco.doctorLab.pojo.request.AutheticationResponse;
import com.medco.doctorLab.repo.UserRepository;

public class AuthService {
	
	@Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;


	
	public AutheticationResponse login(AuthenticationRequest authenticationRequest) {
        String mobile = authenticationRequest.getMobileNumber();
        String otp = authenticationRequest.getOtp();
        String name = authenticationRequest.getName(); 

        // Static OTP verification: only "12345" is valid.
        if (!"12345".equals(otp)) {
            throw new BadCredentialsException("Invalid OTP");
        }

        // Check if the user already exists; if not, create a new user 
        User user = userRepository.findByMobile(mobile);
        if (user == null) {
            user = new User();
            user.setMobile(mobile);
            user.setRole("ROLE_USER"); // Default role 
            user.setName(name);
            userRepository.save(user);
        }
//        } else if (user.getName() == null && name != null) {
//            // Optionally update the user's name if it's missing.
//            user.setName(name);
//            userRepository.save(user);
//        }
        // Generate a JWT token after login
        String token = jwtTokenProvider.generateAccessToken(user);
        String refreshToken = jwtTokenProvider.generateRefreshToken(user);

        // Return the login response containing the token and a success message.
        return new AutheticationResponse(token, "Login successful");
    }



	public AutheticationResponse setMpin(AuthenticationRequest authenticationRequest) {
		
		 if (authenticationRequest.getMobileNumber() == null || authenticationRequest.getMobileNumber().isEmpty()) {
		        throw new IllegalArgumentException("Mobile number cannot be empty.");
		    }

		    if (authenticationRequest.getMpin() == null || authenticationRequest.getMpin().isEmpty()) {
		        throw new IllegalArgumentException("MPIN cannot be empty.");
		    }
		    
		    User user = userRepository.findByMobile(authenticationRequest.getMobileNumber());
		    if (user == null) {
		        throw new RuntimeException("User not found.");
		    }
		    try {
		        userRepository.save(user);
		        
		        return new AutheticationResponse("MPIN set successfully!");
		    } catch (Exception e) {
		        throw new RuntimeException("Error saving MPIN: " + e.getMessage());
		    }
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//  @Autowired
//  private PasswordEncoder passwordEncoder;
//	
	 
//	public String loginWithOtp(String mobileNumber, String otp, String name) {
//		
//		 if (!"12345".equals(otp)) {
//	            throw new RuntimeException("Invalid OTP");
//	        }
//
//	        // Check if user exists
//	        User user = userRepository.findByMobile(mobileNumber);
//	        if (user == null) {
//	            // First-time login → Create user
//	            user = new User();
//	            user.setMobile(mobileNumber);
//	            user.setName(name); 
//	            user.setRole("ROLE_USER"); 
//	            userRepository.save(user);
//	        }
//
//	        // Generate JWT token
//	        return jwtTokenProvider.generateAccessToken(user);
//	    }
//	

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// Set MPIN for the DeliveryBoy
//		public String setMpin(String username, String mpin) {
//			if (username == null || username.isEmpty()) {
//				throw new IllegalArgumentException("Username cannot be empty");
//			}
//
//			if (mpin == null || mpin.isEmpty()) {
//				throw new IllegalArgumentException("MPIN cannot be empty");
//			}
//
//			DeliveryBoy deliveryBoy = deliveryBoyRepository.findByUsername(username);
//
//			if (deliveryBoy == null) {
//				throw new RuntimeException("User not found");
//			}
//
//			// Encode and set MPIN
//			//deliveryBoy.setMpin(passwordEncoder.encode(mpin));
//
//			// Save to database
//			try {
//				deliveryBoyRepository.save(deliveryBoy);
//				return "MPIN set successfully!";
//			} catch (Exception e) {
//				throw new RuntimeException("Error saving MPIN: " + e.getMessage());
//			}
//		}


