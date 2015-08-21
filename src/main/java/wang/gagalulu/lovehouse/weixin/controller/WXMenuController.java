package wang.gagalulu.lovehouse.weixin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import wang.gagalulu.lovehouse.weixin.services.WXMenuService;

@Controller
@RequestMapping("/lovelulu/wxMenu")
public class WXMenuController {
	@Autowired
	private WXMenuService wxMenuService;
	
	
	@ResponseBody
	@RequestMapping(value = "/create")
	public String createMenu(HttpServletRequest request,String content){
		String result = wxMenuService.createMenu(content);
		return result;
	}
}
