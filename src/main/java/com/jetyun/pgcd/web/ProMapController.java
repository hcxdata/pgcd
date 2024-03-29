package com.jetyun.pgcd.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.jetyun.pgcd.system.page.CriteriaUtil;

@Controller
@RequestMapping("/proMap.do")
public class ProMapController extends AbstractController {

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		Map map = CriteriaUtil.fillMap(request);
		return new ModelAndView("/proMap/proMap",map);
	}

}
