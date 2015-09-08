package wang.gagalulu.lovehouse.css.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/lovelulu/css")
public class CssController {
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String cssIndex(){
		return "/css/index";
	}
	@RequestMapping(value = "/{path}", method = RequestMethod.GET)
	public String cssDispatcher(@PathVariable String path){
		return "/css/"+path;
	}
}
