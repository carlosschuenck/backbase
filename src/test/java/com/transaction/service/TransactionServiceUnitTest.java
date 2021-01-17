package com.transaction.service;

import static com.transaction.service.impl.TransactionService.BASE_URL;
import static com.transaction.service.impl.TransactionService.END_URL;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.OK;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.transaction.entity.Transaction;
import com.transaction.entity.TransactionResponse;
import com.transaction.execption.BusinessException;
import com.transaction.service.impl.TransactionService;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceUnitTest {
	
	@InjectMocks
	private TransactionService transactionService;
	
	@Mock
	private RestTemplate restTemplate;
	
	private static String ACCOUNT_ID = "443ko4k433";
	private static String TRANSACTION_TYPE = "teste";
	@Test
	public void testFindAllWithoutAccountId() {
		BusinessException exception = assertThrows(BusinessException.class, () -> {
		  transactionService.findAllByAccountId(null);
	    });
		Transaction transac = new Transaction();
		transac.setAccountId("1");
		assertEquals(TransactionService.ACCCOUNT_ID_VALIDATION_MESSAGE, exception.getMessage());
	}
	
	@Test
	public void testFindByTypeWithoutType() {
		BusinessException exception = assertThrows(BusinessException.class, () -> {
		  transactionService.findByType("12dasdsa", null);
	    });
		Transaction transac = new Transaction();
		transac.setAccountId("1");
		assertEquals(TransactionService.TRANSACTION_TYPE_VALIDATION_MESSAGE, exception.getMessage());
	}
	
	@Test
	public void testFindAllByAccountId() {
		// restTemplate.getForEntity(url, TransactionResponse.class).getBody();
		Transaction transaction = new Transaction();
		TransactionResponse response = new TransactionResponse(Arrays.asList(transaction));
		when(restTemplate.getForEntity(BASE_URL + ACCOUNT_ID + END_URL , TransactionResponse.class))
						 .thenReturn(new ResponseEntity(response, OK));
		
		List<Transaction> result = transactionService.findAllByAccountId(ACCOUNT_ID);
		assertNotNull(result);
		assertFalse(result.isEmpty());
	}
	
	@Test
	public void testFindByType() {
		Transaction transaction = new Transaction();
		transaction.setTransactionType(TRANSACTION_TYPE);
		transaction.setId("id");
		transaction.setCounterPartyName("name");
		transaction.setInstructedAmount(BigDecimal.TEN);
		transaction.setDescription("description");
		transaction.setTransactionCurrency("BRL");
		transaction.setCounterPartyLogoPath("path");
		
		Transaction transaction2 = new Transaction();
		transaction2.setTransactionType("type2");
		
		TransactionResponse response = new TransactionResponse(Arrays.asList(transaction, transaction2));
		when(restTemplate.getForEntity(BASE_URL + ACCOUNT_ID + END_URL , TransactionResponse.class))
						 .thenReturn(new ResponseEntity(response, OK));
		
		List<Transaction> result = transactionService.findByType(ACCOUNT_ID, TRANSACTION_TYPE);
		assertNotNull(result);
		assertFalse(result.isEmpty());
		assertEquals(result.size(), 1);
	}
	
	
	@Test
	public void testGetTotalAmountByTransactionType() {
		Transaction transaction = new Transaction();
		transaction.setTransactionAmount(BigDecimal.TEN);
		transaction.setTransactionType(TRANSACTION_TYPE);
		Transaction transaction2 = new Transaction();
		transaction2.setTransactionAmount(BigDecimal.TEN);
		transaction2.setTransactionType(TRANSACTION_TYPE);
		
		TransactionResponse response = new TransactionResponse(Arrays.asList(transaction, transaction2));
		when(restTemplate.getForEntity(BASE_URL + ACCOUNT_ID + END_URL , TransactionResponse.class))
						 .thenReturn(new ResponseEntity(response, OK));
		
		BigDecimal result = transactionService.getTotalAmountByTransactionType(ACCOUNT_ID, TRANSACTION_TYPE);
		assertNotNull(result);
		assertEquals(result, BigDecimal.valueOf(20));
	}
}
