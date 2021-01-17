package com.transaction.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.transaction.config.TestBeanConfig;
import com.transaction.entity.Transaction;
import com.transaction.service.impl.TransactionService;

@WebAppConfiguration
@ContextConfiguration(classes = { TestBeanConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class TransactionControllerIntegrationTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;
	private static String ACCOUNT_ID = "savings-kids-john";
	private static String TRANSACTION_TYPE = "sepa";
	
	@Before
	public void setup() {
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
		this.mockMvc = builder.build();
	}

	@Test
	public void testFindAllByAccountId() {
		ResultMatcher ok = MockMvcResultMatchers.status().isOk();
		
		try {
			mockMvc.perform(get("/api/transactions/account/" + ACCOUNT_ID)).andExpect(ok);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindByType() {
		ResultMatcher ok = MockMvcResultMatchers.status().isOk();
		
		try {
			mockMvc.perform(get("/api/transactions/account/" + ACCOUNT_ID+ "/transaction-type/"+TRANSACTION_TYPE)).andExpect(ok);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetTotalAmountByTransactionType() {
		ResultMatcher ok = MockMvcResultMatchers.status().isOk();
		try {
			mockMvc.perform(get("/api/transactions/account/" + ACCOUNT_ID+ "/transaction-type/"+TRANSACTION_TYPE+"/amount")).andExpect(ok);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
