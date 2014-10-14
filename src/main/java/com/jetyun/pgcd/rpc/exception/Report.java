package com.jetyun.pgcd.rpc.exception;

/**
 * 类说明:最终生成的错误报告<br>
 * 创建时间: 2013-3-3 下午4:03:16<br>
 * 
 * @author 李祥辉<br>
 * @email lixh@neteast.com<br>
 */
public class Report {
	private String message;
	private String errorCode;

	public Report(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}
