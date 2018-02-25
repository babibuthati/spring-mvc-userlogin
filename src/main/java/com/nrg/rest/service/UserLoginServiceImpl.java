package com.nrg.rest.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.nrg.rest.model.MessageResponse;
import com.nrg.rest.model.User;

@Service
public class UserLoginServiceImpl implements UserLoginService {

	@Value("${user.userName}")
	public String userName;
	
	@Value("${user.pwd}")
	public String pwd;
	
	@Override
	public MessageResponse validateUser(User user) {
		if(user.getUserName().equals(userName)) {
			if(user.getPassword().equals(pwd)){
				return new MessageResponse("Login Successfull");
			} else {
				return new MessageResponse("Invalid password");
			}
		} else {
			return new MessageResponse("Invalid Username");
		}
	}
	
}
