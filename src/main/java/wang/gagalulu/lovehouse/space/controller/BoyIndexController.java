package wang.gagalulu.lovehouse.space.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/lovelulu/space")
public class BoyIndexController {
	@RequestMapping(value = "/boy", method = RequestMethod.GET)
	public String boyIndex(){
		return "/space/boy/index";
	}
	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public String aboutPeople(HttpServletRequest request,String name){
		
		return "/space/boy/about";
	}
}
