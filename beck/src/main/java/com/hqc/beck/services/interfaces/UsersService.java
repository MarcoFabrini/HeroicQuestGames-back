package com.hqc.beck.services.interfaces;

import java.util.List;

import com.hqc.beck.dto.OrdersDTO;
import com.hqc.beck.model.Users;

public interface UsersService {
	List<Users> listAllUsers() throws Exception;

	Users getAuthenticatedUser() throws Exception;

	List<OrdersDTO> getUserOrders() throws Exception;
}// interface
	