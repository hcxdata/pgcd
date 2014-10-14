package com.jetyun.pgcd.system.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class SessionState {
	private HttpSession session;

	public SessionState() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		this.session = request.getSession();
	}
	
	public SessionState(HttpSession session) {
		this.session = session;
	}

	public <T> void save(String name, T obj) {
		session.setAttribute(name, obj);
	}

	public <T> void clear(String name) {
		session.removeAttribute(name);
	}
	
	public <T> T get(String name){
		return (T)session.getAttribute(name);
	}
}
