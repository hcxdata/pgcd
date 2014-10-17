package com.jetyun.pgcd.service.backend.sechdule;

import java.text.ParseException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.CronTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerUtils;

/**
 * trigger 生成
 * 
 * @author yangy
 * 
 */
public class TriggerFactory {

	private static Log loger = LogFactory.getLog(TriggerFactory.class);

	/**
	 * 以时间间隔的方式生成trigger
	 * 
	 * @param unit
	 * @param interval
	 * @param name
	 * @param group
	 * @return
	 */
	public static Trigger getTimeTrigger(TimeUnit unit, int interval,
			String name, String group) {
		Trigger trigger = null;
		if (TimeUnit.HOURS == unit) {
			trigger = TriggerUtils.makeHourlyTrigger(interval);
		} else if (TimeUnit.MINUTES == unit) {
			trigger = TriggerUtils.makeMinutelyTrigger(interval);
		} else if (TimeUnit.SECONDS == unit) {
			trigger = TriggerUtils.makeSecondlyTrigger(interval);
		} else {
			trigger = TriggerUtils.makeMinutelyTrigger(interval);
		}
		trigger.setName(name);
		trigger.setGroup(group);
		trigger.setStartTime(new Date());
		loger.info("create a trigger,name is [" + trigger.getName()
				+ "],group is -[" + trigger.getGroup() + "],start fire is -["
				+ trigger.getStartTime() + "]");
		return trigger;
	}

	/**
	 * 以crontab的方式生成trigger
	 * 
	 * @param name
	 * @param group
	 * @param interval
	 * @return
	 */
	public static Trigger getCrontabTrigger(String name, String group,
			String interval) {
		Trigger trigger = null;
		try {
			trigger = new CronTrigger(name, group, interval);
			loger.info("create a trigger,name is [" + trigger.getName()
					+ "],group is -[" + trigger.getGroup()
					+ "],time fire is -[" + interval + "]");
		} catch (ParseException e) {
			e.printStackTrace();
			loger.error("create a trigger error,message is " + e.getMessage());
		}
		return trigger;
	}
}
