package com.hqc.back.services.interfaces;

import java.util.List;

import com.hqc.back.dto.OrdersDTO;
import com.hqc.back.model.Users;

public interface UsersService {
	List<Users> listAllUsers() throws Exception;

	Users getAuthenticatedUser() throws Exception;

	List<OrdersDTO> getUserOrders() throws Exception;
}// interface
	