package com.nrg.rest.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.github.springtestdbunit.DbUnitTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="file:src/main/webapp/WEB-INF/spring/root-context.xml")
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class })
@WebAppConfiguration
public class UserLoginControllerIntegrationTest {
	
	@Value("${user.userName}")
	public String userName;
	
	@Value("${user.pwd}")
	public String pwd;
	
	@Autowired
    private WebApplicationContext webApplicationContext;
	
	private MockMvc mockMvc;

	@Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
    }
	
	@Test
	public void test_Valid_UserLogin() throws Exception {
		this.mockMvc.perform(post("/userlogin")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"userName\" : \"" + userName +"\",\"password\" : \"" + pwd +"\"}"))
				.andExpect(status().isOk())
		.andExpect(jsonPath("$.returnMessage").value("Login Successfull"));
		
	}
	
	@Test
	public void test_InValid_UserLogin() throws Exception {
		String invalidUserName = "invalidUser";
		this.mockMvc.perform(post("/userlogin")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"userName\" : \"" + invalidUserName +"\",\"password\" : \"" + pwd +"\"}"))
				.andExpect(status().isOk())
		.andExpect(jsonPath("$.returnMessage").value("Invalid Username"));
	}
	
	@Test
	public void test_Invalid_Password() throws Exception {
		String invalidPasword = "invalidPwd";
		this.mockMvc.perform(post("/userlogin")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"userName\" : \"" + userName +"\",\"password\" : \"" + invalidPasword +"\"}"))
				.andExpect(status().isOk())
		.andExpect(jsonPath("$.returnMessage").value("Invalid password"));
	}

}
