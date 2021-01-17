package com.transaction.service;

import java.math.BigDecimal;
import java.util.List;

import com.transaction.entity.Transaction;

public interface ITransactionService {

	
	List<Transaction> findAllByAccountId(String accountId);
	
	List<Transaction> findByType(String accountId, String type);
	
	BigDecimal getTotalAmountByTransactionType(String accountId, String type);
}
