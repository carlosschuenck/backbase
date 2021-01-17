package com.transaction.execption;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 792458327425913617L;

	public BusinessException(String message) {
        super(message);
    }
}
