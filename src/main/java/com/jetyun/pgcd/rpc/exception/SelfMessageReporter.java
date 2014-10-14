package com.jetyun.pgcd.rpc.exception;

import org.springframework.stereotype.Component;

/**
 * 类说明:自定义异常生成报告类<br>
 * 创建时间: 2013-3-3 下午4:35:49<br>
 * 
 * @author 李祥辉<br>
 * @email lixh@neteast.com<br>
 */
@Component
public class SelfMessageReporter implements Reporter {

	@Override
	public Report report(ExceptionSource source) {
		return new Report(source.getCaseException().getMessage());
	}

	@Override
	public Boolean canReport(ExceptionSource source) {
		if (source.getCaseException() instanceof ME)
			return true;
		return false;
	}

}
