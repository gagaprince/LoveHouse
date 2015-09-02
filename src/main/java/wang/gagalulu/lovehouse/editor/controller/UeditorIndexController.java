package wang.gagalulu.lovehouse.editor.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import wang.gagalulu.lovehouse.bean.pojo.DailyModel;
import wang.gagalulu.lovehouse.bean.pojo.ResultJsonModel;

import com.alibaba.fastjson.JSON;
import com.baidu.ueditor.ActionEnter;

@Controller
@RequestMapping("/lovelulu/ueditor")
public class UeditorIndexController {
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String ueditorIndex(){
		return "/ueditor/index";
	}
	
	@RequestMapping(value = "/home")
	@ResponseBody
	public String homeInterface(HttpServletRequest request){
		ServletContext application = request.getSession().getServletContext();
		String rootPath = application.getRealPath( "/" );
		return new ActionEnter( request, rootPath ).exec();
	}
	
}
