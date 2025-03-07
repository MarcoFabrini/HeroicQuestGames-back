package com.hqc.beck.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hqc.beck.model.Users;
import com.hqc.beck.services.implementation.UsersImplementation;

@RequestMapping("/api")
@RestController
public class UsersController {
	private final UsersImplementation usersService;

	public UsersController(UsersImplementation userService) {
		this.usersService = userService;
	}// UsersController

	@GetMapping("/user/users/getAuthenticatedUser")
	public ResponseEntity<?> getAuthenticatedUser() throws Exception {
		try {
			Users user = usersService.getAuthenticatedUser();
			return ResponseEntity.ok(user);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}// getAuthenticatedUser

	@GetMapping("/admin/users/listAllUsers")
	public ResponseEntity<List<Users>> listAllUsers() throws Exception {
		List<Users> users = usersService.listAllUsers();

		return ResponseEntity.ok(users);
	}// listAllUsers

}// class
