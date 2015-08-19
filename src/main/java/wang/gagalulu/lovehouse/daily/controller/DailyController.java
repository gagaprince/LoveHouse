package wang.gagalulu.lovehouse.daily.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import wang.gagalulu.lovehouse.annotation.WXShareController;

@Controller
@RequestMapping("/lovelulu/daily")
public class DailyController {
	@WXShareController
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String goIndex(HttpServletRequest request , String dailyId,Model model){
		model.addAttribute("title", "写给我最爱的璐璐");
		return "/dailys/myDaily";
	}
}
