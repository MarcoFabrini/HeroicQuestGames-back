package com.hqc.back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hqc.back.model.Users;
import com.hqc.back.request.LoginUsersRequest;
import com.hqc.back.request.RegisterUsersRequest;
import com.hqc.back.response.LoginResponse;
import com.hqc.back.services.implementation.AuthenticateImplementation;
import com.hqc.back.services.implementation.JwtImplementation;



@RequestMapping("/api/auth")
@RestController
public class AuthenticationController {
	private final JwtImplementation jwtImplementation;

	private final AuthenticateImplementation authenticateImplementation;

	public AuthenticationController(JwtImplementation jwtService,
			AuthenticateImplementation authenticateImplementation) {
		this.jwtImplementation = jwtService;
		this.authenticateImplementation = authenticateImplementation;
	}// AuthenticationController

	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody RegisterUsersRequest req) throws Exception {
		try {
			Users registeredUser = authenticateImplementation.signup(req);
			return ResponseEntity.ok(registeredUser);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}// signup

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginUsersRequest req) {
		Users authenticatedUser = authenticateImplementation.login(req);
		String jwtToken = jwtImplementation.generateToken(authenticatedUser);
		LoginResponse loginResponse = new LoginResponse().setToken(jwtToken)
				.setExpiresIn(jwtImplementation.getExpirationTime());

		return ResponseEntity.ok(loginResponse);
	}// authenticate

}// class
