package org.zt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zt.dao.UserMapper;
import org.zt.model.request.UserLoginRequest;
import org.zt.model.request.UserRegisterRequest;
import org.zt.model.response.UserLoginResponse;
import org.zt.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public boolean userRegister(UserRegisterRequest userRegisterRequest) {
		int rows=userMapper.userRegister(userRegisterRequest);
		System.out.println(rows);
		return rows>0?true:false;
	}

	@Override
	public UserLoginResponse userLogin(UserLoginRequest userLoginRequest) {
		
		UserLoginResponse userLoginResponse=userMapper.userLogin(userLoginRequest);
		return userLoginResponse;
	}

}
