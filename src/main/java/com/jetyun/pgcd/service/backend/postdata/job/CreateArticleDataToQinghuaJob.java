package com.jetyun.pgcd.service.backend.postdata.job;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.jetyun.pgcd.service.backend.postdata.ArticleBag;
import com.jetyun.pgcd.service.backend.postdata.ArticleDataDao;
import com.jetyun.pgcd.service.backend.postdata.PgcConfig;



public class CreateArticleDataToQinghuaJob implements Job {

	private static ObjectMapper mapper = new ObjectMapper();

	private static long interval = 1000 * 60;

	private static final ArticleDataDao articleDao = new ArticleDataDao();

	private static Log log = LogFactory
			.getLog(CreateArticleDataToQinghuaJob.class);

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		long start = getStartDate();
		long end = getEndDate(start);
		long startKey = Long.MAX_VALUE - start;
		long endKey = Long.MAX_VALUE - end;
		log.info("startKey=[" + startKey + "],endKey=[" + endKey
				+ "]");
		ArticleBag bag = null;
		StringBuffer sb = new StringBuffer();
		File file = new File(PgcConfig.getDataPath()+File.separator+"article_json_"+getDateString(new Date(), "yyyyMMddHHmm")+".txt");
		try {
			bag = articleDao.getArticles(startKey, endKey,
					PgcConfig.getDataLimit());
			if (!CollectionUtils.isEmpty(bag.getArticles())) {
				sb.append(mapper.writeValueAsString(bag));
				if(file!=null){
					FileUtils.write(file, sb.toString(), "utf-8");
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private long getEndDate(long end) {
		return end - interval * PgcConfig.getPushInterval();
	}

	private long getStartDate() {
		return System.currentTimeMillis();
	}
	
	private String getDateString(Date date,String format){
		DateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}

	public static void main(String[] args) throws JobExecutionException {
		CreateArticleDataToQinghuaJob job = new CreateArticleDataToQinghuaJob();
		job.execute(null);
	}
}
