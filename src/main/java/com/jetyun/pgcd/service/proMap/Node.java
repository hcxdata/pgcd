package com.jetyun.pgcd.service.proMap;

public class Node {
	private String id;
	private String label;
	private String size;
	private String x;
	private String y;
	private String color;
	private String forceLabel = "true";

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getForceLabel() {
		return forceLabel;
	}

	public void setForceLabel(String forceLabel) {
		this.forceLabel = forceLabel;
	}

}
