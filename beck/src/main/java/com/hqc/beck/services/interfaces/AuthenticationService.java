package com.hqc.beck.services.interfaces;

import com.hqc.beck.model.Users;
import com.hqc.beck.request.LoginUsersRequest;
import com.hqc.beck.request.RegisterUsersRequest;

public interface AuthenticationService {
	Users signup(RegisterUsersRequest req) throws Exception;

	Users login(LoginUsersRequest req) throws Exception;
}// interface
