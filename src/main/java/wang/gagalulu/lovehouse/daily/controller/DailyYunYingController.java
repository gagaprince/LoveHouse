package wang.gagalulu.lovehouse.daily.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import wang.gagalulu.lovehouse.bean.pojo.DailyModel;
import wang.gagalulu.lovehouse.bean.pojo.ResultJsonModel;
import wang.gagalulu.lovehouse.daily.services.DailyService;

import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("/lovelulu/daily")
public class DailyYunYingController {
	@Autowired
	private DailyService dailyService;
	
	private static final Logger logger =  Logger.getLogger(DailyYunYingController.class);
	@RequestMapping(value = "/yunying", method = RequestMethod.GET)
	public String yunying(HttpServletRequest request ,Long dailyId,Model model){
		if(dailyId!=null){
			DailyModel dailyModel = dailyService.iWantADaily(dailyId);
			if(dailyModel!=null){
				model.addAttribute("dailyModel",dailyModel);
			}
		}
		return "/dailys/yunying/myDailyYunYing";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public String saveMsg(HttpServletRequest request,DailyModel daily){
		ResultJsonModel model = new ResultJsonModel();
		try {
			dailyService.saveOrUpdateDaily(daily);
			model.setData(daily);
		} catch (Exception e) {
			e.printStackTrace();
			model.getBstatus().setCode(201);
			model.getBstatus().setDesc("保存失败");
		}
		return JSON.toJSONString(model);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	@ResponseBody
	public String deleteDaily(HttpServletRequest request,DailyModel daily){
		ResultJsonModel model = new ResultJsonModel();
		try {
			dailyService.deleteDaily(daily);
		} catch (Exception e) {
			e.printStackTrace();
			model.getBstatus().setCode(202);
			model.getBstatus().setDesc("删除失败");
		}
		return JSON.toJSONString(model);
	}
}
