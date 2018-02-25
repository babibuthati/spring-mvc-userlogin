package com.nrg.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nrg.rest.model.MessageResponse;
import com.nrg.rest.model.User;
import com.nrg.rest.service.UserLoginService;

@Controller
public class UserLoginController {
	
	@Autowired
	private UserLoginService userLoginService;

	@RequestMapping(value = "/userlogin", method = RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public MessageResponse userLogin(@RequestBody User user) {
		return userLoginService.validateUser(user);
	}

}
