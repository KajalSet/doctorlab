package com.medco.deoctorLab.filters;

import java.io.IOException;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;



@Component
public class JwtAuthFilter extends OncePerRequestFilter {

	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	 @Override
	    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	            throws ServletException, IOException {
	        String token = getJwtFromRequest(request);
	        if (token != null && jwtTokenProvider.validateToken(token)) {
	            String mobileNUmber = jwtTokenProvider.getUsernameFromToken(token);
	            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(mobileNUmber, null, null);
	            SecurityContextHolder.getContext().setAuthentication(authentication);
	        }
	        filterChain.doFilter(request, response);
	    }
	 
	 private String getJwtFromRequest(HttpServletRequest request) {
			String bearerToken = request.getHeader("Authorization");
			if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
				return bearerToken.substring(7);
			}
			return null;
		}

	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//
//		String token = getJwtFromRequest(request); // Extract JWT from request
//
//		if (token != null && jwtTokenProvider.validateToken(token)) {
//			// Extract mobile number from token
//			String mobileNumber = jwtTokenProvider.getUsernameFromToken(token);
//
//			// Extract user role from token claims
//			Claims claims = jwtTokenProvider.extractClaims(token);
//			String role = claims.get("role", String.class);
//
////			System.out.println("Extracted Token: " + token);
////			System.out.println("Extracted Mobile Number: " + mobileNumber);
////			System.out.println("Extracted Role: " + role);
//
//			if (role == null) {
//				role = "ROLE_USER"; // Default role if missing
//			}
//
//			// Create authentication object
//			List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(role));
//			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(mobileNumber,
//					null, authorities);
//
//			SecurityContextHolder.getContext().setAuthentication(authentication);
//		}
//
//		// Continue the filter chain
//		filterChain.doFilter(request, response);
//	}

	

//	  @Override
//	  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//	          throws ServletException, IOException {
//	      String token = getJwtFromRequest(request);
//	      
//	      if (token != null && jwtTokenProvider.validateToken(token)) {
//	          String mobileNumber = jwtTokenProvider.getUsernameFromToken(token); // This returns the mobile number
//	          String role = jwtTokenProvider.extractClaims(token).get("role", String.class); // Extract role
//
//	          if (role != null) {
//	              List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(role));
//
//	              UsernamePasswordAuthenticationToken authentication =
//	            		    new UsernamePasswordAuthenticationToken(mobileNumber, null, List.of(new SimpleGrantedAuthority(role)));
//
//	              SecurityContextHolder.getContext().setAuthentication(authentication);
//	          }
//	      }
//	      
//	      filterChain.doFilter(request, response);
//	  }

//	    @Override
//	    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//	            throws ServletException, IOException {
//	        String token = getJwtFromRequest(request);
//	        if (token != null && jwtTokenProvider.validateToken(token)) {
//	            String username = jwtTokenProvider.getUsernameFromToken(token);
//	            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null, null);
//	            SecurityContextHolder.getContext().setAuthentication(authentication);
//	        }
//	        filterChain.doFilter(request, response);
//	    }

}
