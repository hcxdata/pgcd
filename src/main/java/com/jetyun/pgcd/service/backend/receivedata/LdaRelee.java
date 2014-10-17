package com.jetyun.pgcd.service.backend.receivedata;

import java.util.List;

public class LdaRelee {

	private List<LdaReleeReltt> reltt;
	
	private String topicID;

	public List<LdaReleeReltt> getReltt() {
		return reltt;
	}

	public void setReltt(List<LdaReleeReltt> reltt) {
		this.reltt = reltt;
	}

	public String getTopicID() {
		return topicID;
	}

	public void setTopicID(String topicID) {
		this.topicID = topicID;
	}
}
