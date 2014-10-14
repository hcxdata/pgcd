/**
 * 
 */
package com.jetyun.pgcd.util;

import java.lang.reflect.Field;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author seraph
 * 
 */
public class BeanUtils extends org.apache.commons.beanutils.BeanUtils {

	private static final Log log = LogFactory.getLog(BeanUtils.class);

	public static Object getPropertyAsObject(Object bean, String name) {
		Field[] fields = bean.getClass().getDeclaredFields();
		Field.setAccessible(fields, true);
		Object obj = null;
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];

			if (name.equals(field.getName())) {
				try {
					obj = field.get(bean);
				} catch (IllegalArgumentException e) {
					log.error(e.getMessage());
				} catch (IllegalAccessException e) {
					log.error(e.getMessage());
				}
			}
		}
		return obj;
	}

}
