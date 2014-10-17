package com.jetyun.pgcd.service.backend.postdata;

import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;

public class PgcConfig {

	private static ResourceBundle pgc;

	private static String pgcTriggerTime = "0 0/10 * * * ?";

	private static int dataLimit = 5000;

	private static int pushInterval = 10;
	
	private static String bakPath;
	
	private static String posturl;
	
	private static String dataPath;

	static {
		pgc = ResourceBundle.getBundle("backend/pgc", Locale.getDefault());
		if (!StringUtils.isBlank(pgc.getString("pgcTriggerTime"))) {
			pgcTriggerTime = pgc.getString("pgcTriggerTime");
		}
		if (!StringUtils.isBlank(pgc.getString("dataLimit"))) {
			dataLimit = Integer.parseInt(pgc.getString("dataLimit"));
		}
		if (!StringUtils.isBlank(pgc.getString("pushInterval"))) {
			pushInterval = Integer.parseInt(pgc.getString("pushInterval"));
		}
		if (!StringUtils.isBlank(pgc.getString("bakPath"))) {
			bakPath = pgc.getString("bakPath");
		}
		if (!StringUtils.isBlank(pgc.getString("posturl"))) {
			posturl = pgc.getString("posturl");
		}
		if (!StringUtils.isBlank(pgc.getString("dataPath"))) {
			dataPath = pgc.getString("dataPath");
		}
		
		
	}

	public static String getDataPath() {
		return dataPath;
	}

	public static String getBakPath() {
		return bakPath;
	}

	public static String getPgcTriggerTime() {
		return pgcTriggerTime;
	}

	public static int getDataLimit() {
		return dataLimit;
	}

	public static int getPushInterval() {
		return pushInterval;
	}
	
	
	
	public static String getPosturl() {
		return posturl;
	}

	public static void main(String[] args) {
		System.out.println(PgcConfig.getBakPath());
	}

}
