package com.jetyun.pgcd.rpc.exception;

import java.util.ResourceBundle;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

/**
 * 类说明:错误翻译为用户可读信息数据源<br>
 * 创建时间: 2013-3-3 下午3:36:57<br>
 * 
 * @author 李祥辉<br>
 * @email lixh@neteast.com<br>
 */
@Component
public class MessageBundleSource {
	public static final String PROPERTIES_FILE_NAME = "i18n.exception.message";
	public static ResourceBundle resource;

	@PostConstruct
	public void init() {
		resource = ResourceBundle.getBundle(PROPERTIES_FILE_NAME);
	}

	public static String getMessage(String key) {
		if (resource.containsKey(key))
			return resource.getString(key);
		else
			return null;
	}
}
