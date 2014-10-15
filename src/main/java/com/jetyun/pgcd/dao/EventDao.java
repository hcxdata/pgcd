package com.jetyun.pgcd.dao;

import org.springframework.stereotype.Component;

import com.jetyun.pgcd.dao.domain.Event;
import com.jetyun.pgcd.system.pageTag.Pagination;

@Component
public class EventDao extends BasicDao<Event> {
	public Pagination<Event> getEventPage(int page) {
		return this.getPage("event.getEventList", page);
	}
}
