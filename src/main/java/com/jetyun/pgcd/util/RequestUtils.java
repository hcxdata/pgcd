/**
 * 
 */
package com.jetyun.pgcd.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

/**
 * Description:<br>
 * Origin Time: 2009-7-10 下午01:31:32<br>
 * 
 * @author Seraph<br>
 * @email:seraph115@gmail.com<br>
 */
public class RequestUtils {

	public static Object getParameter(HttpServletRequest request, String name,
			Class<?> clazz) {

		// TODO: Convert parameters according class name
		return null;
	}

	public static Object getParameter(HttpServletRequest request, String name,
			String defaultValue, Class<?> clazz) {

		// TODO: Convert parameters according class name
		return null;
	}

	@SuppressWarnings("unchecked")
	public static void fillForm(HttpServletRequest request, Object form) {

		try {
			List<String> clazzFieldNames = getFieldNames(form.getClass());
			List<String> superClazzFieldNames = getFieldNames(form.getClass()
					.getSuperclass());

			List<String> fieldNames = new LinkedList<String>();
			fieldNames.addAll(clazzFieldNames);
			fieldNames.addAll(superClazzFieldNames);

			Enumeration<String> enu = request.getParameterNames();
			while (enu.hasMoreElements()) {
				String parameterName = (String) enu.nextElement();
				String parameterValue = request.getParameter(parameterName);
				if (fieldNames.contains(parameterName)
						&& StringUtils.isNotBlank(parameterValue.toString())) {
					Object value = null;
					// Handle the super class fields
					if (superClazzFieldNames.contains(parameterName)) {
						value = transfondparameterType(form.getClass()
								.getSuperclass(), parameterName, parameterValue);
					} else {
						value = transfondparameterType(form.getClass(),
								parameterName, parameterValue);
					}
					BeanUtils.setProperty(form, parameterName, value);
				}
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public static Object getForm(HttpServletRequest request, Class<?> clazz) {

		Object form = new Object();
		try {
			form = clazz.newInstance();
			fillForm(request, form);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return form;
	}

	private static List<String> getFieldNames(Class<?> clazz) {
		Field[] fields = clazz.getDeclaredFields();
		Field.setAccessible(fields, true);
		List<Field> fieldList = Arrays.asList(fields);
		List<String> fieldNames = new LinkedList<String>();
		for (Field field : fieldList) {
			field.setAccessible(true);
			fieldNames.add(field.getName());
		}
		return fieldNames;
	}

	private static Object fieldConverter(String typeName, String parameterValue) {
		try {
			if ("java.util.Date".equals(typeName)) {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				return formatter.parse(parameterValue);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return parameterValue;
	}

	private static Object transfondparameterType(Class<?> clazz,
			String parameterName, String parameterValue) {

		Object obj = null;
		try {
			Field field = clazz.getDeclaredField(parameterName);
			Class<?> fieldType = field.getType();
			String typeName = fieldType.getName();
			obj = fieldConverter(typeName, parameterValue);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		return obj;
	}

}
