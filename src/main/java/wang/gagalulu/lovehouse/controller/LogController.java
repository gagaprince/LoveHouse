package wang.gagalulu.lovehouse.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/lovelulu/log")
public class LogController {
	private static final Logger logger =  Logger.getLogger(LogController.class);
	@RequestMapping(value = "/liskey", method = RequestMethod.GET)
	@ResponseBody
	public String routeIndex(HttpServletRequest request, Model model){
		logger.info("getFromIp:"+getRemortIP(request)+"  Lulu:"+request.getParameter("txt"));
		return "";
	}
	
	public String getRemortIP(HttpServletRequest request) {
//		if (request.getHeader("x-forwarded-for") == null) {
			return request.getRemoteHost();
//		}
//		return request.getHeader("x-forwarded-for");
	}
}
