package org.zt.service;

import org.zt.model.request.UserLoginRequest;
import org.zt.model.request.UserRegisterRequest;
import org.zt.model.response.UserLoginResponse;

public interface UserService {

	boolean userRegister(UserRegisterRequest userRegisterRequest);

	UserLoginResponse userLogin(UserLoginRequest userLoginRequest);
	
	
}
