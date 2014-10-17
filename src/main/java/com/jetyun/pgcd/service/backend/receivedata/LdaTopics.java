package com.jetyun.pgcd.service.backend.receivedata;

import java.util.List;

public class LdaTopics {

	private String labelName;
	
	private List<LdaTopicsNewsDetail> newsDetail;
	
	private List<LdaTopicsSpeIssueSet> speIssueSet;

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	public List<LdaTopicsNewsDetail> getNewsDetail() {
		return newsDetail;
	}

	public void setNewsDetail(List<LdaTopicsNewsDetail> newsDetail) {
		this.newsDetail = newsDetail;
	}

	public List<LdaTopicsSpeIssueSet> getSpeIssueSet() {
		return speIssueSet;
	}

	public void setSpeIssueSet(List<LdaTopicsSpeIssueSet> speIssueSet) {
		this.speIssueSet = speIssueSet;
	}
	
	
}
