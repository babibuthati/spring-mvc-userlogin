package com.nrg.rest.service;

import org.springframework.stereotype.Service;

import com.nrg.rest.model.MessageResponse;
import com.nrg.rest.model.User;

@Service
public interface UserLoginService {

	public MessageResponse validateUser(User user);
}
