package com.transaction.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transaction.entity.Transaction;
import com.transaction.service.ITransactionService;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin
public class TransactionController {
	
	@Autowired
	private ITransactionService transactionService;

	@GetMapping("/account/{accountId}")
	public ResponseEntity<List<Transaction>> findAllByAccountId(@PathVariable("accountId") String accountId) {
		return ResponseEntity.ok(transactionService.findAllByAccountId(accountId));
	}
	
	@GetMapping("/account/{accountId}/transaction-type/{type}")
	public ResponseEntity<List<Transaction>> findByType(@PathVariable("accountId") String accountId, @PathVariable("type") String type) {
		return ResponseEntity.ok(transactionService.findByType(accountId, type));
	}
	
	@GetMapping("/account/{accountId}/transaction-type/{type}/amount")
	public ResponseEntity<BigDecimal> getTotalAmountByTransactionType(@PathVariable("accountId") String accountId, @PathVariable("type") String type) {
		return ResponseEntity.ok(transactionService.getTotalAmountByTransactionType(accountId, type));
	}
}
