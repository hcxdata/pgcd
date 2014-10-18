package com.jetyun.pgcd.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jetyun.pgcd.dao.ContentInfoDao;
import com.jetyun.pgcd.web.domain.HTimeLineDate;
import com.jetyun.pgcd.web.domain.HTimeLineInfo;
import com.jetyun.pgcd.web.domain.HTimeLineInfoAsset;
import com.jetyun.pgcd.web.domain.HTimeline;

@Controller
@RequestMapping("/hTimeline.do")
public class HTimelineController {

	@Autowired
	private ContentInfoDao contentInfoDao;

	@RequestMapping(params = "method=list")
	protected String list(String eventID, ModelMap model,
			HttpServletRequest request) throws Exception {
		HTimeline htl = new HTimeline();
		HTimeLineInfo htli = getHTimeLineInfo();
		List<HTimeLineDate> htld = contentInfoDao.getHTimeLineDateList(eventID);
		ObjectMapper objectMapper = new ObjectMapper();
		htli.setDate(htld);
		htl.setTimeline(htli);
		model.addAttribute("timelineData",
				objectMapper.writeValueAsString(htl));
		return "/htimeline/htimeline";
	}
	private HTimeLineInfo  getHTimeLineInfo(){
		HTimeLineInfo htli = new HTimeLineInfo();
		htli.setHeadline("马航MH17航班在乌俄边境坠毁全记录");
		htli.setType("default");
		htli.setText("马航客机MH17在靠近俄罗斯的乌克兰边境坠毁，飞机载有298人。这架客机原定由荷兰阿姆斯特丹飞往马来西亚首都吉隆坡。据报道飞机是被击落坠毁，乌克兰方面确认机上无一人生还。");
		htli.setStartDate("");
		HTimeLineInfoAsset asset = new HTimeLineInfoAsset();
		asset.setMedia("http://i2.sinaimg.cn/dy/iframe/timeline/2014/0718/U11182P1T994D4F25930DT20140718100208.jpg");
		htli.setAsset(asset);
		return htli;
	}
}
