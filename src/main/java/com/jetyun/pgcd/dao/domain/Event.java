package com.jetyun.pgcd.dao.domain;

public class Event {
	private int id;
	private String description;
	private String featureWord;
	private int newsNum;
	private String createdOn;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
}
