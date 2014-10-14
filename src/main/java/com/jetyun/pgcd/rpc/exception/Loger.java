package com.jetyun.pgcd.rpc.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.commons.logging.LogFactory;

public class Loger {
	public static void log(ExceptionSource source) {
		org.apache.commons.logging.Log log = LogFactory.getLog(source
				.getAction());
		if (log.isDebugEnabled()) {
			log.debug("---- Debugging information ----");
			log.debug("cause-message\t:"
					+ source.getCaseException().getMessage());
			log.debug("cause-exception\t:"
					+ source.getCaseException().getClass().getCanonicalName());
			log.debug("actionName\t:" + source.getActionName());
			log.debug("params\t:" + source.getJsonparams());
			log.debug("---------------------------------");
		}
		log.error(getExceptionTrace(source.getCaseException()));
	}

	private static String getExceptionTrace(Throwable e) {
		if (e != null) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			return sw.toString();
		}
		return "No Excepton";
	}
}
