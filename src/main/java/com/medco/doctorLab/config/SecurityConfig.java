package com.medco.doctorLab.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.medco.deoctorLab.filters.JwtAuthFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	private final JwtAuthFilter jwtAuthFilter;

	public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
		this.jwtAuthFilter = jwtAuthFilter;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable()).cors(cors -> cors.configurationSource(corsConfigurationSource()))
				.authorizeRequests(auth -> auth
						// Allow Swagger & Public Endpoints
						.antMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
						.antMatchers("/index", "/api/auth/login", "/api/auth/register").permitAll()

						// Allow setting MPIN without JWT
						.antMatchers("/api/auth/set-mpin").permitAll()

					//	.antMatchers("/api/orders/**").permitAll()


					//	.antMatchers("/api/deliveryboys/**").hasAuthority("ADMIN")

						// Authenticate Everything Else
						.anyRequest().authenticated())
				//added .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
		 
		return http.build();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(List.of("*")); // Allow all origins
		configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		configuration.setAllowedHeaders(List.of("*"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
	
	

}
