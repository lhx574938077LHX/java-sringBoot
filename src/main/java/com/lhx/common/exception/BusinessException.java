package com.lhx.common.exception;

/**
 * 业务异常
 * 2016年7月26日
 * */
public class BusinessException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String code;
	
	public BusinessException(String code, String message) {
		super( message );
		this.code = code;
	}
	
	
	public String getCode() {
		return code;
	}

}
