package com.jetyun.pgcd.service.backend.servlet;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.quartz.Trigger;

import com.jetyun.pgcd.service.backend.postdata.PgcConfig;
import com.jetyun.pgcd.service.backend.postdata.job.CreateArticleDataToQinghuaJob;
import com.jetyun.pgcd.service.backend.postdata.job.PushArticleDataToQinghuaJob;
import com.jetyun.pgcd.service.backend.sechdule.QuartzScheduler;
import com.jetyun.pgcd.service.backend.sechdule.TriggerFactory;

public class ScheduleServlet extends GenericServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7214932287707319095L;

	@Override
	public void init() throws ServletException {
		try {
			QuartzScheduler.startSchedule();
			JobDetail createDataJob = QuartzScheduler.getQuartzJob(CreateArticleDataToQinghuaJob.class.getName(), CreateArticleDataToQinghuaJob.class, CreateArticleDataToQinghuaJob.class.getName());
			Trigger createDataTrigger = TriggerFactory.getCrontabTrigger(CreateArticleDataToQinghuaJob.class.getName(), CreateArticleDataToQinghuaJob.class.getName(), PgcConfig.getPgcTriggerTime());
			JobDetail pushDataJob = QuartzScheduler.getQuartzJob(PushArticleDataToQinghuaJob.class.getName(), PushArticleDataToQinghuaJob.class, PushArticleDataToQinghuaJob.class.getName());
			Trigger pushDataTrigger = TriggerFactory.getCrontabTrigger(PushArticleDataToQinghuaJob.class.getName(), PushArticleDataToQinghuaJob.class.getName(), PgcConfig.getPgcTriggerTime());
			QuartzScheduler.addJob(createDataJob, createDataTrigger);
			QuartzScheduler.addJob(pushDataJob, pushDataTrigger);
		} catch (SchedulerException e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
		super.init();
	}
	
	@Override
	public void service(ServletRequest arg0, ServletResponse arg1)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
