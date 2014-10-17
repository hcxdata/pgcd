package com.jetyun.pgcd.service.backend.postdata;

import org.apache.http.StatusLine;

public class PostResult {

	private StatusLine status;
	
	private String message;

	public StatusLine getStatus() {
		return status;
	}

	public void setStatus(StatusLine status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
