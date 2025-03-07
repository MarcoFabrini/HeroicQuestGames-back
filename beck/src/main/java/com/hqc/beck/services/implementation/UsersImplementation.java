package com.hqc.beck.services.implementation;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.hqc.beck.dto.OrdersDTO;
import com.hqc.beck.model.Users;
import com.hqc.beck.repository.UsersRepository;
import com.hqc.beck.services.interfaces.UsersService;

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
