package com.takamol.roboagent.gateway.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.takamol.roboagent.gateway.service.UserRepository;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final JwtTokenFilter jwtTokenFilter;

	public SecurityConfig(UserRepository userRepository, JwtTokenFilter jwtTokenFilter) {
		this.jwtTokenFilter = jwtTokenFilter;
	}
	// Details omitted for brevity

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Enable CORS and disable CSRF
		http = http.cors().and().csrf().disable();

		// Set session management to stateless
		http = http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and();

		// Set unauthorized requests exception handler
		http = http.exceptionHandling().authenticationEntryPoint((request, response, ex) -> {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
		}).and();
		/* for the unauthorized access
		 * System.out.println("Unauthorized Access"); responseBody.setComplaints(null);
		 * responseBody.setResult("110");
		 * responseBody.setResultDescription("Unauthorized Access");
		 * response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		 * response.getWriter().write(new Gson().toJson(responseBody));
		 */
		
		// Set permissions on endpoints
		http.authorizeRequests()
				// Our public endpoints
				.antMatchers(HttpMethod.POST, "/RoboAgent/get-answer").permitAll()
				.antMatchers(HttpMethod.POST, "/api/users/signin").permitAll()
				.antMatchers(HttpMethod.POST, "/api/users/signup").permitAll()
				.antMatchers(HttpMethod.POST, "/api/users/signupforadmin").permitAll()
				.antMatchers(HttpMethod.GET, "/api/users/currentuser").permitAll()
				.antMatchers(HttpMethod.POST, "/api/users/signout").permitAll()
				.antMatchers(HttpMethod.GET, "/api/complaints").hasAnyRole("ADMIN", "USER")
				.antMatchers(HttpMethod.GET, "/api/complaints/{\\d+}").hasAnyRole("ADMIN", "USER")
				.antMatchers(HttpMethod.POST, "/api/complaints").hasAnyRole("ADMIN", "USER")
				.antMatchers(HttpMethod.PUT, "/api/complaints/{\\d+}").hasAnyRole("ADMIN", "USER")
				.antMatchers(HttpMethod.PUT, "/api/complaints/updatestatus/{\\d+}").hasRole("ADMIN").antMatchers("/**").permitAll();
				
		
		// Our private endpoints

		// Add JWT token filter
		http.addFilterAfter(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}