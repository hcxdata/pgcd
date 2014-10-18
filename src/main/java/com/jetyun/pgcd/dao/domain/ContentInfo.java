package com.jetyun.pgcd.dao.domain;

public class ContentInfo {

	private long id ;
	private long topicID ;
	private String title;
	private String tim;
	private String url;
	private String rowkey;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getTopicID() {
		return topicID;
	}
	public void setTopicID(long topicID) {
		this.topicID = topicID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTim() {
		return tim;
	}
	public void setTim(String tim) {
		this.tim = tim;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getRowkey() {
		return rowkey;
	}
	public void setRowkey(String rowkey) {
		this.rowkey = rowkey;
	}

}
