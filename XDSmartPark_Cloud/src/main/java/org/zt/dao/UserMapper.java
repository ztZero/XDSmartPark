package org.zt.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.zt.model.request.UserLoginRequest;
import org.zt.model.request.UserRegisterRequest;
import org.zt.model.response.UserLoginResponse;

@Mapper
public interface UserMapper {
	
	@Insert("insert into user(username,userpassword) values(#{userName},#{userPassword})")
	int userRegister(UserRegisterRequest userRegisterRequest);
	
	@Select("select userid,username,phone from user where username=#{userName} and userpassword=#{userPassword}")
	UserLoginResponse userLogin(UserLoginRequest userLoginRequest);

}
