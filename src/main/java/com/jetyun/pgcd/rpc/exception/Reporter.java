package com.jetyun.pgcd.rpc.exception;

/**
 * 类说明:生成异常报告的抽象接口<br>
 * 创建时间: 2013-3-3 下午4:06:10<br>
 * 
 * @author 李祥辉<br>
 * @email lixh@neteast.com<br>
 */
public interface Reporter {
	public Report report(ExceptionSource source);

	public Boolean canReport(ExceptionSource source);
}
