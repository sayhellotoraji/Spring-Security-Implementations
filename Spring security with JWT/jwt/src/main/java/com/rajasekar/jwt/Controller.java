package com.rajasekar.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rajasekar.jwt.models.AuthenticationRequest;
import com.rajasekar.jwt.models.AuthenticationResponse;
import com.rajasekar.jwt.services.MyUserDetailsService;
import com.rajasekar.jwt.util.JwtUtil;



@RestController
public class Controller {
	
	//Code order 2
	@Autowired
	private AuthenticationManager authenticationManager;
	
	//Code order 4
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	
	//Code order 7
	@Autowired
	private JwtUtil jwtTokenUtil;
	
	

	@GetMapping("/hello")
	public String home() {
		return "Hello World";
	}
	
	
	//Since the every url needs to be authenticated we need to create an exception for this end point alone in SecurityConfiguration class.
	@PostMapping("/authenticate")
	

	
	// code order 1
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
			);
		}
		catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}


		//Code order 3
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		//Code order 5
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		
		//Code order 6
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
	
}
