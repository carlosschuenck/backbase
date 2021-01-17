package com.transaction.controller;

import static org.junit.Assert.assertThrows;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.NestedServletException;

import com.transaction.config.TestBeanConfig;

@WebAppConfiguration
@ContextConfiguration(classes = { TestBeanConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class JwtAuthenticationControllerIntegrationTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
		this.mockMvc = builder.build();
	}

	@Test
	public void testLogin() {
		ResultMatcher ok = MockMvcResultMatchers.status().isOk();

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/api/authenticate")
				.contentType(MediaType.APPLICATION_JSON).content("{\"username\": \"admin\",\"password\": \"admin\"}");

		try {
			mockMvc.perform(builder).andExpect(ok);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testLoginFail() {
		ResultMatcher ok = MockMvcResultMatchers.status().is5xxServerError();

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/api/authenticate")
				.contentType(MediaType.APPLICATION_JSON).content("{\"username\": \"xxxx\",\"password\": \"xxxx\"}");

		NestedServletException exception = assertThrows(NestedServletException.class, () -> {
			mockMvc.perform(builder).andExpect(ok);
		});

	}

}
