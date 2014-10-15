package com.jetyun.pgcd.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jetyun.pgcd.dao.ContentInfoDao;
import com.jetyun.pgcd.web.domain.HTimeline;

@Controller
@RequestMapping("/hTimeline.do")
public class HTimelineController {

	@Autowired
	private ContentInfoDao contentInfoDao;

	@RequestMapping(params = "method=list")
	protected String list(String eventID, ModelMap model,
			HttpServletRequest request) throws Exception {
		List<HTimeline> list = contentInfoDao.getHTimeLineList(eventID);
		ObjectMapper objectMapper = new ObjectMapper();
		model.addAttribute("timelineData",
				objectMapper.writeValueAsString(list));
		return "/htimeline/htimeline";
	}
}
