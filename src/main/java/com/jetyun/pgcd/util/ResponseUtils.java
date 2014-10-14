/**
 * 
 */
package com.jetyun.pgcd.util;

import javax.servlet.http.HttpServletResponse;

/**
 * Description:<br>
 * Origin Time: 2009-7-10 下午02:59:51<br>
 * 
 * @author Seraph<br>
 * @email:seraph115@gmail.com<br>
 */
public class ResponseUtils {

	public static final String DEFAULT_CHARSET = "utf-8";

	/**
	 * Method: 设置Ajax返回类型的响应头<br>
	 * Origin Time: 2009-7-10 下午03:02:22<br>
	 * 
	 * @author: Seraph<br>
	 * @param response
	 */
	public static void setJsonHeader(HttpServletResponse response) {
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Expires", "0");
		response.setHeader("Content-Type", "text/xml; charset="
				+ DEFAULT_CHARSET);
		// This method need servlet-api2.4.jar provided
		response.setCharacterEncoding(DEFAULT_CHARSET);
	}

	/**
	 * Method: 设置Ajax返回类型的响应头<br>
	 * Origin Time: 2009-7-10 下午03:02:16<br>
	 * 
	 * @author: Seraph<br>
	 * @param response
	 * @param charset
	 */
	public static void setJsonHeader(HttpServletResponse response,
			String charset) {
		if (charset == null || "".equals(charset.trim())) {
			charset = DEFAULT_CHARSET;
		}
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Expires", "0");
		response.setHeader("Content-Type", "text/xml; charset=" + charset);
		// This method need servlet-api2.4.jar provided
		response.setCharacterEncoding(charset);
	}

}
