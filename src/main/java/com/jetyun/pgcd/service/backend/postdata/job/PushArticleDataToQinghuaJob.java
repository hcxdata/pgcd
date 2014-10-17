package com.jetyun.pgcd.service.backend.postdata.job;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.jetyun.pgcd.service.backend.postdata.ArticleBag;
import com.jetyun.pgcd.service.backend.postdata.FileSuffixFilter;
import com.jetyun.pgcd.service.backend.postdata.HttpClientPost;
import com.jetyun.pgcd.service.backend.postdata.PgcConfig;

public class PushArticleDataToQinghuaJob implements Job {

	private static ObjectMapper mapper = new ObjectMapper();

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		File file = new File(PgcConfig.getDataPath());
		File[] files = file.listFiles(new FileSuffixFilter("txt"));
		if (files == null || files.length == 0) {
			return;
		}
		ArticleBag bag = null;
		List<File> bakFiles = new ArrayList<File>();
		for (File f : files) {
			try {
				bag = addBag(f, bag);
				bakFiles.add(f);
				if (bag.getArticleNum() >= 1000) {
					pushArticleBag(bag);
					bakFiles(bakFiles);
					return;
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new JobExecutionException("job execute fail,message is "
						+ e.getMessage());
			}

		}
	}

	private void bakFiles(List<File> bakFiles) throws IOException {
		if (!CollectionUtils.isEmpty(bakFiles)) {
			for (File f : bakFiles) {
				FileUtils.moveFile(f, new File(PgcConfig.getBakPath()
						+ File.separator + f.getName()));
			}
		}

	}

	private void pushArticleBag(ArticleBag bag) throws JsonGenerationException, JsonMappingException, IOException {
		HttpClientPost.post(PgcConfig.getPosturl(), "", mapper.writeValueAsString(bag));
	}

	private ArticleBag addBag(File f, ArticleBag bag)
			throws JsonParseException, JsonMappingException, IOException {
		if (bag == null) {
			bag = mapper.readValue(f, ArticleBag.class);
			return bag;
		}
		ArticleBag temp = mapper.readValue(f, ArticleBag.class);
		bag.getArticles().addAll(temp.getArticles());
		bag.setArticleNum(bag.getArticleNum() + temp.getArticleNum());
		return bag;
	}
	public static void main(String[] args) throws JobExecutionException {
		PushArticleDataToQinghuaJob job = new PushArticleDataToQinghuaJob();
		
		job.execute(null);
	}

}
