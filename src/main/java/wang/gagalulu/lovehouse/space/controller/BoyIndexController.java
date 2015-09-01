package wang.gagalulu.lovehouse.space.controller;

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
}
