package com.transaction.entity;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.transaction.deserializer.TransactionDeserializer;

@JsonDeserialize(using = TransactionDeserializer.class)
public class Transaction {
	
	private String id;
	private String accountId;
	private String counterPartyAccount;
	private String counterPartyName;
	private String counterPartyLogoPath;
	private BigDecimal instructedAmount;
	private String instructedCurrency;
	private BigDecimal transactionAmount;
	private String transactionCurrency;
	private String transactionType;
	private String description;
	
	public Transaction() {
		super();
	}

	public Transaction(String id, String accountId, String counterPartyAccount, String counterPartyName,
			String counterPartyLogoPath, BigDecimal instructedAmount, String instructedCurrency, BigDecimal transactionAmount,
			String transactionCurrency, String transactionType, String description) {
		super();
		this.id = id;
		this.accountId = accountId;
		this.counterPartyAccount = counterPartyAccount;
		this.counterPartyName = counterPartyName;
		this.counterPartyLogoPath = counterPartyLogoPath;
		this.instructedAmount = instructedAmount;
		this.instructedCurrency = instructedCurrency;
		this.transactionAmount = transactionAmount;
		this.transactionCurrency = transactionCurrency;
		this.transactionType = transactionType;
		this.description = description;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getCounterPartyName() {
		return counterPartyName;
	}
	public void setCounterPartyName(String counterPartyName) {
		this.counterPartyName = counterPartyName;
	}
	public String getCounterPartyLogoPath() {
		return counterPartyLogoPath;
	}
	public void setCounterPartyLogoPath(String counterPartyLogoPath) {
		this.counterPartyLogoPath = counterPartyLogoPath;
	}
	public BigDecimal getInstructedAmount() {
		return instructedAmount;
	}
	public void setInstructedAmount(BigDecimal instructedAmount) {
		this.instructedAmount = instructedAmount;
	}
	public String getInstructedCurrency() {
		return instructedCurrency;
	}
	public void setInstructedCurrency(String instructedCurrency) {
		this.instructedCurrency = instructedCurrency;
	}
	public BigDecimal getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(BigDecimal transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	public String getTransactionCurrency() {
		return transactionCurrency;
	}
	public void setTransactionCurrency(String transactionCurrency) {
		this.transactionCurrency = transactionCurrency;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCounterPartyAccount() {
		return counterPartyAccount;
	}
	public void setCounterPartyAccount(String counterPartyAccount) {
		this.counterPartyAccount = counterPartyAccount;
	}

}
