package com.jetyun.pgcd.service.backend.postdata;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.PageFilter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.util.Bytes;

public class ArticleDataDao {

	private static final Log log = LogFactory.getLog(ArticleDataDao.class);
	
	public ArticleBag getArticles(long startTime, long endTime, int limit)
			throws IOException {
		String indexTable = "webpage_index";
		String tableName = "webpage_new";
		Configuration config = HBaseConfiguration.create();
		HTableInterface htable = new HTable(config, tableName);
		HTableInterface index = new HTable(config, indexTable);
		Scan indexScan = new Scan();
		indexScan.setStartRow((startTime + "").getBytes());
		indexScan.setStopRow((endTime + "").getBytes());
		log.info("fetch article from webpage_index startKey=["+startTime+"],endKey=["+endTime+"]");
		FilterList filter = new FilterList();
		Filter limitFilter = new PageFilter(limit);
		Filter columnValueFilter = new SingleColumnValueFilter("index".getBytes(),"has_content".getBytes(),  CompareFilter.CompareOp.EQUAL,Bytes.toBytes("1"));
		filter.addFilter(limitFilter);
		filter.addFilter(columnValueFilter);
		log.info("fetch webpage_index scan ="+indexScan);
		indexScan.setFilter(filter);
		ResultScanner result = index.getScanner(indexScan);
        		
		Iterator<Result> it = result.iterator();
		ArticleBag bag = new ArticleBag();
		Result temp = null;
		List<Article> articles = new ArrayList<Article>();
		Article article = null;
		Result rs = null;
		while (it.hasNext()) {
			temp = it.next();
			rs = getArticle(temp,htable);
			article = trasferArticle(rs);
			if (article != null){
				articles.add(article);
			}
		}
		bag.setArticleNum(articles.size());
		bag.setArticles(articles);
		htable.close();
		index.close();
		return bag;
	}

	private Result getArticle(Result temp, HTableInterface htable) throws IOException {
		String key = Bytes.toString(temp.getValue("index".getBytes(), "url".getBytes()));
		log.info("fetch url=["+key+"]");
		Get get = new Get(key.getBytes());
		return htable.get(get);
	}

	private Article trasferArticle(Result temp) {
		Article a = new Article();
		byte[] b = null;
		b = temp.getRow();
		if (b != null&&!StringUtils.isBlank(Bytes.toString(b))) {
			a.setId(Bytes.toString(b));
		}
		b = temp.getValue("mtdt".getBytes(), "page_title".getBytes());
		if (b != null&&!StringUtils.isBlank(Bytes.toString(b))) {
			a.setTitle(Bytes.toString(b));
			log.info("fetch one article info-key=["+a.getId()+"],title=["+a.getTitle()+"]");
		} else {
			log.info("no fetch article info-key["+a.getId()+"],title=["+a.getTitle()+"]");
			return null;
		}
		b = temp.getValue("mtdt".getBytes(), "page_content".getBytes());

		if (b != null) {
			a.setContent(Bytes.toString(b));
		}else{
			return null;
		}
		b = temp.getValue("mtdt".getBytes(), "author".getBytes());
		if (b != null) {
			a.setAuthor(Bytes.toString(b));
		}
		b = temp.getValue("mtdt".getBytes(), "column_name".getBytes());
		if (b != null) {
			a.setColumn_name(Bytes.toString(b));
		}
		b = temp.getValue("mtdt".getBytes(), "column_url".getBytes());

		if (b != null) {
			a.setColumn_url(Bytes.toString(b));
		}
		b = temp.getValue("mtdt".getBytes(), "comments_count".getBytes());

		if (b != null) {
			a.setComments_count(Bytes.toString(b));
		}
		
		b = temp.getValue("mtdt".getBytes(), "description".getBytes());

		if (b != null) {
			a.setDescription(Bytes.toString(b));
		}
		b = temp.getValue("mtdt".getBytes(), "keywords".getBytes());

		if (b != null) {
			a.setKeywords(Bytes.toString(b));
		}
		b = temp.getValue("mtdt".getBytes(), "publish_time".getBytes());

		if (b != null) {
			a.setPublishTime(Bytes.toString(b));
		}
		b = temp.getValue("mtdt".getBytes(), "source".getBytes());

		if (b != null) {
			a.setSource(Bytes.toString(b));
		}
		b = temp.getValue("mtdt".getBytes(), "source_url".getBytes());

		if (b != null) {
			a.setSource_url(Bytes.toString(b));
		}
		return a;

	}
}
