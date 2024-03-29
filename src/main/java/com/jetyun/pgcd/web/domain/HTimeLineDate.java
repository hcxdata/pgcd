package com.jetyun.pgcd.web.domain;

public class HTimeLineDate {
	
	private String startDate;
	private String endDate;
	private String headline;
	private String text;
	private HTimeLineDateAsset asset;
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getHeadline() {
		return headline;
	}
	public void setHeadline(String headline) {
		this.headline = headline;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public HTimeLineDateAsset getAsset() {
		return asset;
	}
	public void setAsset(HTimeLineDateAsset asset) {
		this.asset = asset;
	}
	
}
