package com.medco.doctorLab.pojo.request;

public class AutheticationResponse {
	
	 private String token;
	 private String refreshToken;
	 private String message;
	    
	    
	    
		public AutheticationResponse() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		public AutheticationResponse(String token, String message) {
			super();
			this.token = token;
			this.message = message;
		}

		public String getToken() {
			return token;
		}
		public void setToken(String token) {
			this.token = token;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		 public String getRefreshToken() { return refreshToken; }
		    public void setRefreshToken(String refreshToken) { this.refreshToken = refreshToken; }

}
