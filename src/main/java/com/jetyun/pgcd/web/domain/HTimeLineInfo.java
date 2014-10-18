package com.jetyun.pgcd.web.domain;

import java.util.List;

public class HTimeLineInfo {

	private String headline;
	private String type;
	private String text;
	private String startDate;
	private HTimeLineInfoAsset asset;
	private List<HTimeLineDate> date;

	public String getHeadline() {
		return headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public HTimeLineInfoAsset getAsset() {
		return asset;
	}

	public void setAsset(HTimeLineInfoAsset asset) {
		this.asset = asset;
	}

	public List<HTimeLineDate> getDate() {
		return date;
	}

	public void setDate(List<HTimeLineDate> date) {
		this.date = date;
	}
}
