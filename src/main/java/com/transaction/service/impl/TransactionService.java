package com.transaction.service.impl;

import static org.apache.commons.lang3.StringUtils.isBlank;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.transaction.entity.Transaction;
import com.transaction.entity.TransactionResponse;
import com.transaction.execption.BusinessException;
import com.transaction.service.ITransactionService;

@Service
public class TransactionService implements ITransactionService{
	@Autowired
	private RestTemplate restTemplate;
	
	public static String BASE_URL = "https://apisandbox.openbankproject.com/obp/v1.2.1/banks/rbs/accounts/";
	public static String END_URL = "/public/transactions";
	public static String ACCCOUNT_ID_VALIDATION_MESSAGE = "Account id is required!";
	public static String TRANSACTION_TYPE_VALIDATION_MESSAGE = "Type is required!";
	
	@Override
	public List<Transaction> findAllByAccountId(String accountId) {
		if (isBlank(accountId))
			throw new BusinessException(ACCCOUNT_ID_VALIDATION_MESSAGE);
		String url = BASE_URL + accountId + END_URL;
		TransactionResponse response = restTemplate.getForEntity(url, TransactionResponse.class).getBody();
		return response.getTransactions();
	}

	@Override
	public List<Transaction> findByType(String accountId, String type) {
		if (isBlank(type))
			throw new BusinessException(TRANSACTION_TYPE_VALIDATION_MESSAGE);
		return findAllByAccountId(accountId).stream().filter(transaction -> type.equalsIgnoreCase(transaction.getTransactionType())).collect(Collectors.toList());
	}

	@Override
	public BigDecimal getTotalAmountByTransactionType(String accountId, String type) {
		List<Transaction> transactionsByType = findByType(accountId,type);
		BigDecimal amount = BigDecimal.ZERO;
		for (Transaction transaction : transactionsByType) {
			amount = amount.add(transaction.getTransactionAmount());
		}
		return amount;
	}

}
