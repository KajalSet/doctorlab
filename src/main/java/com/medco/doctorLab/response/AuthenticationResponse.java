package com.medco.doctorLab.response;

public class AuthenticationResponse {
	 private String message;
	    private Long userId;
	    private String accessToken;
	    private String refreshToken;

	    // Constructor with all fields
	    public AuthenticationResponse(String message, Long userId, String accessToken, String refreshToken) {
	        this.message = message;
	        this.userId = userId;
	        this.accessToken = accessToken;
	        this.refreshToken = refreshToken;
	    }

	    // Getters and Setters
	    public String getMessage() { return message; }
	    public void setMessage(String message) { this.message = message; }

	    public Long getUserId() { return userId; }
	    public void setUserId(Long userId) { this.userId = userId; }

	    public String getAccessToken() { return accessToken; }
	    public void setAccessToken(String accessToken) { this.accessToken = accessToken; }

	    public String getRefreshToken() { return refreshToken; }
	    public void setRefreshToken(String refreshToken) { this.refreshToken = refreshToken; }
	}



