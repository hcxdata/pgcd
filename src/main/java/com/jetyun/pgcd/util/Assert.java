/**
 * 
 */
package com.jetyun.pgcd.util;

import java.util.Collection;

/**
 * 类说明:<br>
 * 创建时间: 2007-11-16 上午09:34:06<br>
 * 
 * @author 刘岩松<br>
 * @email: seraph115@gmail.com<br>
 */
public class Assert {

	private static final String EMPTY_STRING = "";

	// private static final String NULL_STRING = "null";

	public static void notNull(Object object) {
		notNull(object, "[断言失败] - 此参数不可为空");
	}

	public static void notNull(Object object, String message) {
		if (object == null) {
			throw new IllegalArgumentException(message);
		}
	}

	public static void notEmpty(Object object) {
		notEmpty(object, "[断言失败] - 此参数不可为空");
	}

	public static void notEmpty(Object object, String message) {
		if (object == null || EMPTY_STRING.equals(object)) {
			throw new IllegalArgumentException(message);
		}
	}

	public static boolean isEmpty(Collection<?> collection) {
		return (collection == null || collection.isEmpty());
	}

	public static boolean notEmpty(Collection<?> collection) {
		return !isEmpty(collection);
	}

}
