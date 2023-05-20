package com.paypilot.hello.controller;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
class HelloControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Test
	@WithMockUser(username = "testUser")
	void testHello() throws Exception {
		String name = "testUser";
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/hello?name=" + name))
			.andExpect(status().isOk())
			.andReturn();

		String content = result.getResponse().getContentAsString();
		assertThat(content).isEqualTo("Hello, " + name + "!");
	}
}
