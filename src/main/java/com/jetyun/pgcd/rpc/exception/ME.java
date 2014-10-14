package com.jetyun.pgcd.rpc.exception;

/**
 * 类说明:框架中使用的通知异常类 - MessageException<br>
 * 创建时间: 2013-3-3 下午3:25:47<br>
 * 
 * @author 李祥辉<br>
 * @email lixh@neteast.com<br>
 */
public class ME extends RuntimeException {
	public ME() {
		super();
	}

	public ME(String message) {
		super(message);
	}

	public ME(Throwable thr) {
		initCause(thr);
	}
}
