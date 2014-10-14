package com.jetyun.pgcd.rpc.exception;

import org.springframework.stereotype.Component;

/**
 * 类说明:绑定异常信息处理类<br>
 * 创建时间: 2013-3-3 下午4:24:32<br>
 * 
 * @author 李祥辉<br>
 * @email lixh@neteast.com<br>
 */
@Component
public class BundleMessageReporter implements Reporter {

	public static final String DEFAULT_MESSAGE_KEY = "DefaultMessage";

	@Override
	public Report report(ExceptionSource source) {
		String key = source.getCaseException().getClass().getCanonicalName();
		String message = MessageBundleSource.getMessage(key);
		if (message == null)
			message = MessageBundleSource.getMessage(DEFAULT_MESSAGE_KEY);

		return new Report(message);
	}

	@Override
	public Boolean canReport(ExceptionSource source) {
		if (!(source.getCaseException() instanceof ME))
			return true;
		return false;
	}

}
