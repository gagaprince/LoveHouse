package wang.gagalulu.lovehouse.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import wang.gagalulu.lovehouse.annotation.WXLoginController;
import wang.gagalulu.lovehouse.annotation.WXShareController;

@Controller
@RequestMapping("/lovelulu")
public class IndexController {
	@WXLoginController
	@WXShareController
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String routeIndex(HttpServletRequest request,HttpServletResponse response, Model model){
		model.addAttribute("name", "gaga");
		return "/homeIndex";
	}
	
}
