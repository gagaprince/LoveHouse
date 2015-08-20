package wang.gagalulu.lovehouse.daily.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import wang.gagalulu.lovehouse.annotation.WXShareController;
import wang.gagalulu.lovehouse.bean.pojo.DailyModel;
import wang.gagalulu.lovehouse.daily.bean.DailyContextBean;
import wang.gagalulu.lovehouse.daily.services.DailyService;

@Controller
@RequestMapping("/lovelulu/daily")
public class DailyController {
	@Autowired
	private DailyService dailyService;
	
	@WXShareController
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String goIndex(HttpServletRequest request , String dailyId,Model model){
		DailyModel dailyModel = dailyService.iWantADaily(Long.parseLong(dailyId));
		DailyContextBean dailyContextBean = dailyService.iWantParseDailyModel(dailyModel);
		model.addAttribute("contextBean",dailyContextBean);
		return "/dailys/myDaily";
	}
}
