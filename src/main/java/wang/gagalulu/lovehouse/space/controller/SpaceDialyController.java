package wang.gagalulu.lovehouse.space.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import wang.gagalulu.lovehouse.bean.pojo.ResultJsonModel;
import wang.gagalulu.lovehouse.bean.pojo.SpaceDailyModel;
import wang.gagalulu.lovehouse.space.services.SpaceDailyService;

import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("/lovelulu/space")
public class SpaceDialyController {
	
	@RequestMapping(value = "/ueindex", method = RequestMethod.GET)
	public String ueditorIndex(HttpServletRequest request,Long dailyId,Model model){
		if(dailyId!=null){
			SpaceDailyModel dailyModel = spaceDailyService.iWantADaily(dailyId);
			model.addAttribute("dailyData", JSON.toJSONString(dailyModel));
		}
		return "/ueditor/index";
	}
	
	
	@Autowired
	private SpaceDailyService spaceDailyService;
	@RequestMapping(value = "/saveDaily", method = RequestMethod.POST)
	@ResponseBody
	public String saveMsg(HttpServletRequest request,SpaceDailyModel daily){
		ResultJsonModel model = new ResultJsonModel();
		try {
			spaceDailyService.saveOrUpdateDaily(daily);
			model.setData(daily);
		} catch (Exception e) {
			e.printStackTrace();
			model.getBstatus().setCode(201);
			model.getBstatus().setDesc("保存失败");
		}
		return JSON.toJSONString(model);
	}
}
