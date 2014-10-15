package com.jetyun.pgcd.dao.domain;

public class Event {
	private int id;
	private String desc;
	private String featureWord;
	private int newsNum;
	private String time;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getFeatureWord() {
		return featureWord;
	}
	public void setFeatureWord(String featureWord) {
		this.featureWord = featureWord;
	}
	public int getNewsNum() {
		return newsNum;
	}
	public void setNewsNum(int newsNum) {
		this.newsNum = newsNum;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
}
