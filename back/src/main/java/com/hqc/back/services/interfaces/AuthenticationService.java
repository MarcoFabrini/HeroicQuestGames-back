package com.hqc.back.services.interfaces;

import com.hqc.back.model.Users;
import com.hqc.back.request.LoginUsersRequest;
import com.hqc.back.request.RegisterUsersRequest;

public interface AuthenticationService {
	Users signup(RegisterUsersRequest req) throws Exception;

	Users login(LoginUsersRequest req) throws Exception;
}// interface
