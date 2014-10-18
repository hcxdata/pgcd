package com.jetyun.pgcd.service.backend.sechdule;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

/**
 * quartz 调度 @author yangy
 * 
 */
public class QuartzScheduler {

	private static Scheduler sechduler;

	public static boolean quartz_schedule_start = false;

	private static final Log loger = LogFactory.getLog(QuartzScheduler.class);

	// 生成调度
	private synchronized static Scheduler getScheduler()
			throws SchedulerException {
		if (sechduler == null || sechduler.isShutdown()){
			sechduler = StdSchedulerFactory.getDefaultScheduler();
		}
		return sechduler;
	}

	// 启动调度
	public synchronized static void startSchedule() throws SchedulerException {
		getScheduler().start();
		quartz_schedule_start = true;
	}

	// 关闭调度
	public synchronized static void shutdownSchedule()
			throws SchedulerException {
		if (sechduler != null) {
			sechduler.shutdown(true);
			sechduler = null;
		}
		quartz_schedule_start = false;
	}

	// 生产quartz job
	public static JobDetail getQuartzJob(String jobName, Class jobClass,
			String group) throws SchedulerException {
		JobDetail job = new JobDetail(jobName, group, jobClass);
		return job;
	}

	// 添加job到调度器
	public synchronized static boolean addJob(JobDetail job, Trigger trigger)
			throws SchedulerException {
		if (sechduler == null || sechduler.isShutdown()) {
			loger.error("sechduler is not start or is shutdown --please start sechduler first---");
			return false;
		}
		sechduler.scheduleJob(job, trigger);
		loger.info("--start quartz job ,job =" + job.getName());
		return true;
	}

	public synchronized static boolean deleteJob(String job_group,
			String job_name) throws SchedulerException {
		if (sechduler == null || sechduler.isShutdown()) {
			loger.error("sechduler is not start or is shutdown --please start sechduler first---");
			return false;
		}
		return sechduler.deleteJob(job_name, job_group);
	}

	public synchronized static boolean reFireJob(JobDetail job,
			String trigger_name, String trigger_group)
			throws SchedulerException {
		if (sechduler == null || sechduler.isShutdown()) {
			loger.error("sechduler is not start or is shutdown --please start sechduler first---");
			return false;
		}
		Trigger trigger = sechduler.getTrigger(trigger_name, trigger_group);
		sechduler.scheduleJob(job, trigger);
		return true;
	}

	public static void main(String[] args) throws SchedulerException,
			InterruptedException {
	}
}
