package com.nrg.rest.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Value;

import com.nrg.rest.model.MessageResponse;
import com.nrg.rest.model.User;
import com.nrg.rest.service.UserLoginService;

@RunWith(MockitoJUnitRunner.class)
public class UserLoginControllerJunitTest {
	
	@Value("${user.userName}")
	public String userName;
	
	@Value("${user.pwd}")
	public String pwd;
	

	@Mock
	private UserLoginService userLoginService;
	
	@InjectMocks
	private UserLoginController userLoginController = Mockito.spy(UserLoginController.class);
	

	@Test
	public void userLoginShouldReturnWhatServiceReturns() throws Exception {
		User user = new User();
		user.setUserName(userName);
		user.setPassword(pwd);
		
		MessageResponse value = new MessageResponse("Login Successfull");
		Mockito.when(userLoginService.validateUser(user)).thenReturn(value);
		
		MessageResponse resp = userLoginController.userLogin(user);
		assertThat(resp.returnMessage).isEqualTo("Login Successfull");
	}
	
	@Test
	public void userLoginShouldReturnNulllResponseForNullInput() throws Exception {
		
		MessageResponse resp = userLoginController.userLogin(null);
		assertThat(resp).isNull();
	}
	
	
}
