package org.zt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zt.model.request.UserLoginRequest;
import org.zt.model.request.UserRegisterRequest;
import org.zt.model.response.UserLoginResponse;
import org.zt.service.UserService;

import static org.springframework.web.bind.annotation.RequestMethod.POST;


@Controller
public class UserController {
	
	@Autowired UserService userService;
	
	
	@RequestMapping(value = "/userRegister", method = POST)
	@ResponseBody
	public boolean userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
		
		System.out.println("userName"+userRegisterRequest.getUserName());
		boolean userRegisterResponse=userService.userRegister(userRegisterRequest);
		
		//UserRegisterResponse userRegisterResponse=new UserRegisterResponse();
		
		return userRegisterResponse;
	}
	
	@RequestMapping(value = "/userLogin", method = POST)
	@ResponseBody
	public UserLoginResponse userLogin(@RequestBody UserLoginRequest userLoginRequest) {
		
		System.out.println("userName"+userLoginRequest.getUserName());
		System.out.println("userPassword"+userLoginRequest.getUserPassword());
		UserLoginResponse userLoginResponse=userService.userLogin(userLoginRequest);
		System.out.println("userName"+userLoginResponse.getUserName());
		return userLoginResponse;
	}

}
