package com.hqc.beck.services.implementation;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hqc.beck.model.Users;
import com.hqc.beck.repository.UsersRepository;
import com.hqc.beck.request.LoginUsersRequest;
import com.hqc.beck.request.RegisterUsersRequest;
import com.hqc.beck.services.interfaces.AuthenticationService;

@Service
public class AuthenticateImplementation implements AuthenticationService {

	private final UsersRepository usersRepository;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;

	public AuthenticateImplementation(UsersRepository usersRepository, PasswordEncoder passwordEncoder,
			AuthenticationManager authenticationManager) {
		this.usersRepository = usersRepository;
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;
	}// AuthenticateImplementation

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Users signup(RegisterUsersRequest req) throws Exception {
		if (usersRepository.findByEmail(req.getEmail()).isPresent())
			throw new Exception("User is already present");

		Users user = new Users();
		user.setFullName(req.getFullName());
		user.setEmail(req.getEmail());
		user.setPassword(passwordEncoder.encode(req.getPassword()));
		user.setRoles(req.getRole());
		user.setActive(true);

		return usersRepository.save(user);
	}// signup

	@Override
	public Users login(LoginUsersRequest req) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword()));

		return usersRepository.findByEmail(req.getEmail()).orElseThrow();
	}// login

}// class
