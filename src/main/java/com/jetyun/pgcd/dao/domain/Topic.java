package com.jetyun.pgcd.dao.domain;

public class Topic {
	private Long id;
	private Long eventId;
	private String description;
	private String featureWord;
	private String createdOn;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFeatureWord() {
		return featureWord;
	}

	public void setFeatureWord(String featureWord) {
		this.featureWord = featureWord;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
}
