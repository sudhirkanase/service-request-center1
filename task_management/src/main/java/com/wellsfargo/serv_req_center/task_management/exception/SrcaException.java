package com.wellsfargo.serv_req_center.task_management.exception;

public class SrcaException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int errorId;
	
	public SrcaException(String msg) {
		super(msg);
	}
	
	public SrcaException(String msg, int errorId) {
		super(msg);
		this.errorId = errorId;
	}

	public int getErrorId() {
		return errorId;
	}

	public void setErrorId(int errorId) {
		this.errorId = errorId;
	}
	
}
