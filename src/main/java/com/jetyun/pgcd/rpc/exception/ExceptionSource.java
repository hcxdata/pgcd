package com.jetyun.pgcd.rpc.exception;

import java.lang.reflect.Method;

import org.springframework.stereotype.Component;

/**
 * 类说明:对异常进行打包的类<br>
 * 创建时间: 2013-3-3 下午3:47:05<br>
 * 
 * @author 李祥辉<br>
 * @email lixh@neteast.com<br>
 */
public class ExceptionSource {
	private Throwable caseException;
	private Class action;
	private Method method;
	private Object[] params;
	private String actionName;
	private String methodName;
	private String jsonparams;

	public Throwable getCaseException() {
		return caseException;
	}

	public ExceptionSource(Throwable exception, Class action, Method method,
			String jsonparams) {
		this.caseException = exception;
		this.action = action;
		this.method = method;
		this.jsonparams = jsonparams;

		setActionName(this.action.getCanonicalName());
		setMethodName(this.method.toGenericString());
	}

	public void setCaseException(Throwable caseException) {
		this.caseException = caseException;
	}

	public Class getAction() {
		return action;
	}

	public void setAction(Class action) {
		this.action = action;
	}

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

	public Object[] getParams() {
		return params;
	}

	public void setParams(Object[] params) {
		this.params = params;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getJsonparams() {
		return jsonparams;
	}

	public void setJsonparams(String jsonparams) {
		this.jsonparams = jsonparams;
	}
}
