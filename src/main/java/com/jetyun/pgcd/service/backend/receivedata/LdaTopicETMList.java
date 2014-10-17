package com.jetyun.pgcd.service.backend.receivedata;

import java.util.List;

public class LdaTopicETMList {

	private LdaEntity entity;
	
	private List<LdaRelee> relee;
	
	private LdaRelte relte;
	
	private List<LdaReltt> reltt;
	
	private String title;
	
	private List<LdaTopWords> topWords;
	
	private List<LdaTopics> topics;

	public LdaEntity getEntity() {
		return entity;
	}

	public void setEntity(LdaEntity entity) {
		this.entity = entity;
	}

	public List<LdaRelee> getRelee() {
		return relee;
	}

	public void setRelee(List<LdaRelee> relee) {
		this.relee = relee;
	}

	public LdaRelte getRelte() {
		return relte;
	}

	public void setRelte(LdaRelte relte) {
		this.relte = relte;
	}



	public List<LdaReltt> getReltt() {
		return reltt;
	}

	public void setReltt(List<LdaReltt> reltt) {
		this.reltt = reltt;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<LdaTopWords> getTopWords() {
		return topWords;
	}

	public void setTopWords(List<LdaTopWords> topWords) {
		this.topWords = topWords;
	}

	public List<LdaTopics> getTopics() {
		return topics;
	}

	public void setTopics(List<LdaTopics> topics) {
		this.topics = topics;
	}
}
