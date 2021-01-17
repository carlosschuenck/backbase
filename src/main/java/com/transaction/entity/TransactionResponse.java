package com.transaction.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TransactionResponse {
	
	private List<Transaction> transactions;
	
	public TransactionResponse(@JsonProperty("transactions") List<Transaction> transactions) {
		super();
		this.transactions = transactions;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
}
