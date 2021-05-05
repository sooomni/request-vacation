package com.example.requestvacation.web;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import lombok.extern.slf4j.Slf4j;

@WebMvcTest(UserController.class) 
@Slf4j
public class UserControllerTest {
	@Autowired
	private MockMvc mvc;
	/*
	@Test
	public void post_new() throws Exception {
		mvc.perform(post("/users/new"))
				.andExpect(status().isOk());
	}

	@Test
	public void post_new_dto() throws Exception {
		String id = "sooomni";
		String pw = "test1234";
		String name = "김수민";
		String role = "admin";


		 mvc.perform(post("/users/new")
				.param("usrId", id)
				.param("usrPw", pw)
				.param("usrName", name)
				.param("usrRole", role))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.usrId", is(id)))
		.andExpect(jsonPath("$.usrPw", is(pw)));
	}*/

}
