package com.jetyun.pgcd.rpc.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 类说明:异常处理类<br>
 * 创建时间: 2013-3-3 下午4:08:18<br>
 * 
 * @author 李祥辉<br>
 * @email lixh@neteast.com<br>
 */
@Component
public class ExceptionHandler {
	@Autowired
	private List<Reporter> reporters = new ArrayList<Reporter>();

	public Report report(ExceptionSource source) {
		Report report = null;
		for (Reporter re : getReporters()) {
			if (re.canReport(source)) {
				report = re.report(source);
			}
		}
		return report;
	}

	public List<Reporter> getReporters() {
		return reporters;
	}

	public void setReporters(List<Reporter> reporters) {
		this.reporters = reporters;
	}
}
