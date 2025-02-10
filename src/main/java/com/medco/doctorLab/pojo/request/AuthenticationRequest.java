package com.medco.doctorLab.pojo.request;

import lombok.Data;

@Data
public class AuthenticationRequest {
	
	 private String mobileNumber;
	    private String otp;
	    private String name;
	    
	    public String getMobileNumber() { return mobileNumber; }
	    public void setMobileNumber(String mobileNumber) { this.mobileNumber = mobileNumber; }

	    public String getOtp() { return otp; }
	    public void setOtp(String password) { this.otp = otp; }

	    public String getName() { return name; }
	    public void setName(String name) { this.name = name; }
	}    


