package wang.gagalulu.lovehouse.luceneindex.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import wang.gagalulu.lovehouse.luceneindex.services.DuanziService;

@Controller
@RequestMapping("/createIndex")
public class CreateIndexController {
	@Autowired
	private DuanziService duanziService;
	
	@RequestMapping(value="/duanzi", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String createDuanziIndex(HttpServletRequest request) throws IOException{
		return duanziService.createDuanziIndex();
	}
}
