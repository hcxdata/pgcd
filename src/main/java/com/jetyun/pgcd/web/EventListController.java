package com.jetyun.pgcd.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jetyun.pgcd.dao.EventDao;
import com.jetyun.pgcd.dao.domain.Event;
import com.jetyun.pgcd.system.pageTag.Pagination;

@Controller
@RequestMapping("/eventList.do")
public class EventListController {

	@Autowired
	EventDao eventDao;

	@RequestMapping(params = "method=list")
	public String list(Integer page, ModelMap model, HttpServletRequest request) {
		if (page == null)
			page = 1;
		Pagination<Event> pageInfo = eventDao.getEventPage(page);
		model.addAttribute("page", pageInfo);
		return "/event/eventList";
	}

}
