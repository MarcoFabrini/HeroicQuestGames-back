package com.hqc.back.services.implementation;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.hqc.back.dto.OrdersDTO;
import com.hqc.back.model.Users;
import com.hqc.back.repository.UsersRepository;
import com.hqc.back.services.interfaces.UsersService;

@Service
public class UsersImplementation implements UsersService {
	private final UsersRepository usersRepository;

	public UsersImplementation(UsersRepository userRepository) {
		this.usersRepository = userRepository;
	}// UsersImplementation

	@Override
	public Users getAuthenticatedUser() throws Exception {
		Authentication authentication = SecurityContextHolder
				.getContext()
				.getAuthentication();

		Users currentUser = (Users) authentication.getPrincipal();

		return currentUser;
	}// authenticatedUser

	

	@Override
	public List<Users> listAllUsers() throws Exception {
		Users currentUser = getAuthenticatedUser(); // Ottieni l'utente autenticato
		return usersRepository.findAllByIdNot(currentUser.getId()); // Escludi il suo ID
	}// listAllUsers

	@Override
	public List<OrdersDTO> getUserOrders() throws Exception {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getUserOrders'");
	}

}// class
