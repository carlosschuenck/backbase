package com.transaction.entity;

import java.io.Serializable;

public class JwtResponse implements Serializable {
	
	private static final long serialVersionUID = 8004210724764554977L;
	
	private final String jwttoken;

	public JwtResponse(String jwttoken) {
		this.jwttoken = jwttoken;
	}

	public String getToken() {
		return this.jwttoken;
	}

}