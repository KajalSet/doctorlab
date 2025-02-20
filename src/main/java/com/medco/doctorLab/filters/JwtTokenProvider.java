package com.medco.doctorLab.filters;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.medco.doctorLab.models.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;




@Component
public class JwtTokenProvider {
	 @Value("${app.token.secret}")
	    private String jwtSecret;


	    @Value("${app.jwtExpirationMs}")
	    private Long accessTokenValidity;
	    
	    @Value("${app.jwtRefreshExpirationMs}")
		private Long refreshTokenValidity;

	    
	    //
	   
//		public String generateAccessToken(User user) {
//	        return Jwts.builder()
//	                .setSubject(user.getMobile()) // Use mobile number as identifier
//	                .claim("id", user.getId())
//	                .claim("role", user.getRole())
//	                .setIssuedAt(new Date())
//	                .setExpiration(new Date(System.currentTimeMillis() + accessTokenValidity))
//	                .signWith(SignatureAlgorithm.HS256, jwtSecret)
//	                
//	                .compact();
//	    }
	
	    public String generateAccessToken(User user) {
		    SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));

		    return Jwts.builder()
		            .setSubject(user.getMobile())
		            .claim("id", user.getId())
		            .claim("role", user.getRole())
		            .setIssuedAt(new Date())
		            .setExpiration(new Date(System.currentTimeMillis() + accessTokenValidity))
		            .signWith(key, SignatureAlgorithm.HS256) // Updated signing method
		            .compact();
		}
	// Generate Refresh Token
//	public String generateRefreshToken(User user) {
//	    return Jwts.builder()
//	            .setSubject(user.getMobile()) // Use mobile number 
//	            .setIssuedAt(new Date())
//	            .setExpiration(new Date(System.currentTimeMillis() + refreshTokenValidity))
//	            .signWith(SignatureAlgorithm.HS512, jwtSecret)
//	            .compact();
//	}
		public String generateRefreshToken(User user) {
		    return Jwts.builder()
		            .setSubject(user.getMobile()) // Use mobile number
		            .setIssuedAt(new Date())
		            .setExpiration(new Date(System.currentTimeMillis() + refreshTokenValidity))
		            .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes()), SignatureAlgorithm.HS512)
		            .compact();
		}
		
		public boolean validateToken(String token) {
		    try {
		        Jwts.parserBuilder()
		            .setSigningKey(getSigningKey()) // Use a Key instead of a plain string
		            .build()
		            .parseClaimsJws(token);
		        return true;
		    } catch (JwtException | IllegalArgumentException e) {
		        System.out.println("JWT Validation Failed: " + e.getMessage());
		        return false;
		    }
		}

		public Claims extractClaims(String token) {
		    return Jwts.parserBuilder()
		            .setSigningKey(getSigningKey()) // Use a Key
		            .build()
		            .parseClaimsJws(token)
		            .getBody();
		}

		public Authentication getAuthentication(String token) {
		    Claims claims = extractClaims(token); // Use refactored method

		    String mobileNumber = claims.getSubject();
		    String role = claims.get("role", String.class); // Extract role from claims

		    if (role == null) {
		        throw new RuntimeException("Role is missing in token");
		    }

		    List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(role));

		    return new UsernamePasswordAuthenticationToken(mobileNumber, null, authorities);
		}

		public String getUsernameFromToken(String token) {
		    return extractClaims(token).getSubject(); // Use refactored method
		}

		// New method to get a valid signing key
		private Key getSigningKey() {
		    return Keys.hmacShaKeyFor(jwtSecret.getBytes());
		}

		
		
	// Validate Token
	
//	public boolean validateToken(String token) {
//		try {
//			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
//			return true;
//		} catch (JwtException | IllegalArgumentException e) {
//			System.out.println("JWT Validation Failed: " + e.getMessage());
//			return false;
//		}
//	}
//	
//
//
//
//	public Claims extractClaims(String token) {
//		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
//	}
//	
//	
//	
//	public Authentication getAuthentication(String token) {
//	    // Use the actual jwtSecret variable injected via @Value instead of a literal string
//	    Claims claims = Jwts.parser().setSigningKey(jwtSecret)
//	            .parseClaimsJws(token).getBody();
//
//	    // Since we're using mobile number as the subject, we can call it mobileNumber
//	    String mobileNumber = claims.getSubject();
//	    String role = claims.get("role", String.class); // Extract role from claims
//
//	    if (role == null) {
//	        throw new RuntimeException("Role is missing in token");
//	    }
//
//	    // Create authorities based on the role provided in the token
//	    List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(role));
//
//	    // Return an Authentication object containing the mobile number as the principal
//	    return new UsernamePasswordAuthenticationToken(mobileNumber, null, authorities);
//	}
//	
//	
//
//	public String getUsernameFromToken(String token) {
//	    Claims claims = Jwts.parser()
//	            .setSigningKey(jwtSecret)
//	            .parseClaimsJws(token)
//	            .getBody();
//	    return claims.getSubject(); // This will return the subject -mobile number
//	}

//	public Long getUserIdFromToken(String token) {
//		return extractClaims(token).get("id", Long.class);
//	}
//
//	public String getAddressFromToken(String token) {
//		return extractClaims(token).get("address", String.class);
//	}

	}
	

//import java.util.Collections;
//import java.util.Date;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.stereotype.Component;
//
//import com.medco.doctorLab.models.User;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.JwtException;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//
//@Component
//public class JwtTokenProvider {
//	
//	 @Value("${app.token.secret}")
//	    private String jwtSecret;
//
//	    @Value("${app.jwtExpirationMs}")
//	    private Long accessTokenValidity;
//	    
//	    @Value("${app.jwtRefreshExpirationMs}")
//		private Long refreshTokenValidity;
//
//	    public String generateAccessToken(User user) {
//	        return Jwts.builder()
//	                .setSubject(user.getMobile()) // Use mobile number as identifier
//	                .claim("id", user.getId())
//	                .claim("role", user.getRole())
//	                .setIssuedAt(new Date())
//	                .setExpiration(new Date(System.currentTimeMillis() + accessTokenValidity))
//	                .signWith(SignatureAlgorithm.HS256, jwtSecret)
//	                .compact();
//	    }
//	// Generate Refresh Token
//	public String generateRefreshToken(User user) {
//	    return Jwts.builder()
//	            .setSubject(user.getMobile()) // Use mobile number 
//	            .setIssuedAt(new Date())
//	            .setExpiration(new Date(System.currentTimeMillis() + refreshTokenValidity))
//	            .signWith(SignatureAlgorithm.HS512, jwtSecret)
//	            .compact();
//	}
//
//	// Validate Token
//	public boolean validateToken(String token) {
//		try {
//			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
//			return true;
//		} catch (JwtException | IllegalArgumentException e) {
//			System.out.println("JWT Validation Failed: " + e.getMessage());
//			return false;
//		}
//	}
//	
//
//
//	public Claims extractClaims(String token) {
//		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
//	}
//	
//	
//	public Authentication getAuthentication(String token) {
//	    // Use the actual jwtSecret variable injected via @Value instead of a literal string
//	    Claims claims = Jwts.parser().setSigningKey(jwtSecret)
//	            .parseClaimsJws(token).getBody();
//
//	    // Since we're using mobile number as the subject, we can call it mobileNumber
//	    String mobileNumber = claims.getSubject();
//	    String role = claims.get("role", String.class); // Extract role from claims
//
//	    if (role == null) {
//	        throw new RuntimeException("Role is missing in token");
//	    }
//
//	    // Create authorities based on the role provided in the token
//	    List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(role));
//
//	    // Return an Authentication object containing the mobile number as the principal
//	    return new UsernamePasswordAuthenticationToken(mobileNumber, null, authorities);
//	}
//	
//	
//	public String getUsernameFromToken(String token) {
//	    Claims claims = Jwts.parser()
//	            .setSigningKey(jwtSecret)
//	            .parseClaimsJws(token)
//	            .getBody();
//	    return claims.getSubject(); // This will return the subject -mobile number
//	}
//
//	public Long getUserIdFromToken(String token) {
//		return extractClaims(token).get("id", Long.class);
//	}
//
//	public String getAddressFromToken(String token) {
//		return extractClaims(token).get("address", String.class);
//	}
//
//}
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
////	public Authentication getAuthentication(String token) {
////		Claims claims = Jwts.parser().setSigningKey("jwtSecret") // Make sure this matches token signing key
////				.parseClaimsJws(token).getBody();
////
////		String username = claims.getSubject();
////		String role = claims.get("role", String.class); // Extract role
////
////		if (role == null) {
////			throw new RuntimeException("Role is missing in token");
////		}
////
////		List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(role));
////
////		return new UsernamePasswordAuthenticationToken(username, null, authorities);
////	}
//
////	// Get Username from Token
////	public String getUsernameFromToken(String token) {
////		Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
////		return claims.getSubject();
////	}
////
//	
