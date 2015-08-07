package wang.gagalulu.lovehouse.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import wang.gagalulu.lovehouse.annotation.WXShareController;

@Controller
@RequestMapping("/lovelulu")
public class IndexController {
	@WXShareController
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String routeIndex(HttpServletRequest request, Model model){
		model.addAttribute("name", "gaga");
		return "/homeIndex";
	}
	
}
