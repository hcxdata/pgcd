package com.jetyun.pgcd.service.backend.receivedata;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.codehaus.jackson.map.ObjectMapper;

public class ClusterResult {

	private String keyword;

	private String uid;

	private Integer topicnum;

	private String username;

	private String leftcontent;

	public Integer getTopicnum() {
		return topicnum;
	}

	public void setTopicnum(Integer topicnum) {
		this.topicnum = topicnum;
	}

	private List<String> wiki;

	private ClusterLdaResult lda;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getLeftcontent() {
		return leftcontent;
	}

	public void setLeftcontent(String leftcontent) {
		this.leftcontent = leftcontent;
	}

	public List<String> getWiki() {
		return wiki;
	}

	public void setWiki(List<String> wiki) {
		this.wiki = wiki;
	}

	public ClusterLdaResult getLda() {
		return lda;
	}

	public void setLda(ClusterLdaResult lda) {
		this.lda = lda;
	}

	public static void main(String[] args) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		String json = FileUtils.readFileToString(new File("E:\\test.txt"),
				"utf-8").replaceAll("\"ID\"", "\"id\"");
		ClusterResult cluster = mapper.readValue(json, ClusterResult.class);
		System.out.println(cluster.getKeyword());
	}
}
