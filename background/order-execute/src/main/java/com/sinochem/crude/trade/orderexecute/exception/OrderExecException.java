package com.sinochem.crude.trade.orderexecute.exception;

public class OrderExecException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8640142095883394282L;
	
	private String code;
	private String message;
	private String[] params;
	
	public OrderExecException(String code, String message) {
		this(code, message, "");
	}
	public OrderExecException(String code, String message, String... params) {
		this.code = code;
		this.message = message;
		this.params = params;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String[] getParams() {
		return params;
	}

	public void setParams(String[] params) {
		this.params = params;
	}

	public OrderExecException() {
		super();
	}

	public OrderExecException(String message) {
		super(message);
	}
	
	public OrderExecException(Throwable cause) {
		super(cause);
	}

	public OrderExecException(String message, Throwable cause) {
		super(message, cause);
	}

}
