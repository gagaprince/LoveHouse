package wang.gagalulu.lovehouse.daily.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/lovelulu/daily")
public class DailyYunYingController {
	@RequestMapping(value = "/yunying", method = RequestMethod.GET)
	public String yunying(HttpServletRequest request ,Model model){
		return "/dailys/yunying/myDailyYunYing";
	}
}
