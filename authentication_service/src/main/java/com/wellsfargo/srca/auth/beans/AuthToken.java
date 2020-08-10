package com.wellsfargo.srca.auth.beans;

import java.io.Serializable;

/**
 * 
 * holds token issued after authentication
 *
 */
public class AuthToken implements Serializable {

	private static final long serialVersionUID = 1L;
	String token;

	public AuthToken(String key) {
		super();
		this.token = key;
	}

	public String getKey() {
		return token;
	}

	public void setKey(String key) {
		this.token = key;
	}
}
