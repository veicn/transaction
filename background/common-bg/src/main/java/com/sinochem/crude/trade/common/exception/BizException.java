package com.sinochem.crude.trade.common.exception;

public class BizException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	/**
	 * 错误编码
	 */
	private String code;
	
	private String type;
	
	private transient Object data;

	public BizException() {
		super();
	}

	public BizException(String message) {
		super(message);
	}
	
	public BizException(Throwable cause) {
		super(cause);
	}

	public BizException(String message, Throwable cause) {
		super(message, cause);
	}

	public BizException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
