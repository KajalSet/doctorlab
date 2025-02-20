package com.medco.doctorLab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.medco.doctorLab.pojo.request.AuthenticationRequest;
import com.medco.doctorLab.response.AuthenticationResponse;
import com.medco.doctorLab.service.AuthService;


@CrossOrigin(origins="*")
@RestController
@RequestMapping(value="api/auth")
public class AuthController {
	
	@Autowired
	 private  AuthService authService;
	 
	
	 @PostMapping("/login")
     public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
     AuthenticationResponse response = authService.login(request);
     return ResponseEntity.ok(response);   
     
 }
	 @PostMapping("/setmpin")
	 public String setMpin(@RequestParam Long id, @RequestParam String mpin) {
	     return authService.setMpin(id, mpin);
	 }
}















//
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//
//import com.medco.doctorLab.pojo.request.AuthenticationRequest;
//import com.medco.doctorLab.pojo.request.AutheticationResponse;
//
//import com.medco.doctorLab.service.AuthService;
//
//import io.swagger.v3.oas.annotations.parameters.RequestBody;
//import io.swagger.v3.oas.annotations.tags.Tag;
//
//@CrossOrigin(origins="*")
//@RestController
//@RequestMapping("/api/auth")
//@Tag(name="User Authentication",description = "APIs for User Authentication related operations")
//
//public class AuthController {
//	
//	 private final AuthService authService;
//
//	    public AuthController(AuthService authService) {
//	        this.authService = authService;
//	    }
//	    
//	    
////    @PostMapping("/login")
////    public ResponseEntity<?> login(@RequestBody AuthenticationRequest request) {
////        String token = authService.loginWithOtp(request.getMobileNumber(), request.getOtp(), request.getName());
////        return ResponseEntity.ok(new LoginResponse(token, "Login successful"));
////    }
//	
////    @PostMapping("/login")
////    public ResponseEntity<AutheticationResponse> login(@RequestBody AuthenticationRequest request) {
////        AutheticationResponse response = authService.login(request);
////        return ResponseEntity.ok(response);   
////        
////    }
////    
//    //pass string mobileno and mpin 
//   
////    @PostMapping("/set-mpin")
////    public ResponseEntity<AutheticationResponse> setMpin(@RequestBody AuthenticationRequest request) {
////        AutheticationResponse response = authService.setMpin(request);
////        return ResponseEntity.ok(response);
////    }
//
//	}
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
////	
////	 @Autowired
////	 private JwtTokenProvider jwtTokenProvider;
////	 
////	 @Autowired
////	 private UserRepository userRepository;
////	 
////	@Autowired
////	private AuthService authService;
////	
////	@PostMapping("/login")
////    public ResponseEntity<AutheticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest) {
////        AutheticationResponse response = authService.login(authenticationRequest);
////        return ResponseEntity.ok(response);
////    }
////	
////	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
////	@PostMapping(value="/login")
////    public ResponseEntity<?> login(@RequestBody SignupRequest signupRequest) {
////        String mobile = signupRequest.getMobile();
////        String otp = signupRequest.getOtp();
////        String name = signupRequest.getName();  // Capture the user's name
////
////        // Static OTP verification: only "12345" is valid.
////        if (!"12345".equals(otp)) {
////            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid OTP");
////        }
////
////        // Check if the user already exists; if not, create a new user with the provided name
////        User user = userRepository.findByMobile(mobile);
////        if (user == null) {
////            user = new User();
////            user.setMobile(mobile);
////            user.setRole("ROLE_USER"); // Default role for new signups
////            user.setName(name);        // Save the name provided by the user
////            userRepository.save(user);
////        } 
////        else {
////            // Optionally, if the user exists but doesn't have a name set, update it.
////            if(user.getName() == null && name != null) {
////                user.setName(name);
////                userRepository.save(user);
////            }
////        }
////
////        // Generate a JWT token after login/signup
////        String token = jwtTokenProvider.generateAccessToken(user);
////        return ResponseEntity.ok(new LoginResponse(token, "Login successful"));
////    }
//
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
////    public ResponseEntity<?> signup(@RequestBody AuthenticationRequest authenticationRequest) {
////        String mobile = authenticationRequest.getMobileNumber();
////        String otp = authenticationRequest.getOtp();
////
////        // Static OTP verification: only "12345" is valid.
////        if (!"12345".equals(otp)) {
////            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid OTP");
////        }
////
////        // Check if the user already exists; if not, create a new user
////        User user = userRepository.findByMobile(mobile);
////        if (user == null) {
////            user = new User();
////            user.setMobile(mobile);
////            user.setRole("ROLE_USER"); // Default role for new signups
////            userRepository.save(user);
////        }
////
////        // Generate a JWT token after signup
////        String token = jwtTokenProvider.generateAccessToken(user);
////        return ResponseEntity.ok(new LoginResponse(token, "Signup successful"));
////    }
////	
////}
////	
////	@PostMapping("/set-mpin")
////	public String setMpin(@RequestParam String username, @RequestParam String mpin) {
////	return authService.setMpin(username, mpin);
////}
////
////	
//	//@postMapping(value="sign-out")
//	
//	
//
//
