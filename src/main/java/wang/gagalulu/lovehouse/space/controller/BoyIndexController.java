package wang.gagalulu.lovehouse.space.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import wang.gagalulu.lovehouse.bean.pojo.SongModel;
import wang.gagalulu.lovehouse.bean.pojo.SpaceDailyModel;
import wang.gagalulu.lovehouse.space.services.SpaceDailyService;

@Controller
@RequestMapping("/lovelulu/space")
public class BoyIndexController {
	
	@Autowired
	private SpaceDailyService spaceDailyService;
	
	@RequestMapping(value = "/boy", method = RequestMethod.GET)
	public String boyIndex(HttpServletRequest request,Model model){
		List<SpaceDailyModel> latestDailys =  spaceDailyService.iWantLatestDailys(5);
		model.addAttribute("LatestDailys", latestDailys);
		SongModel song = spaceDailyService.iWantASong();
		model.addAttribute("song", song);
		return "/space/boy/index";
	}
	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public String aboutPeople(HttpServletRequest request,String name){
		
		return "/space/boy/about";
	}
	
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String detailDaily(HttpServletRequest requst,long id ,Model model){
		List<SpaceDailyModel> latestDailys =  spaceDailyService.iWantLatestDailys(5);
		model.addAttribute("LatestDailys", latestDailys);
		SpaceDailyModel daily = spaceDailyService.iWantADaily(id);
		model.addAttribute("daily", daily);
		SongModel song = spaceDailyService.iWantASong();
		model.addAttribute("song", song);
		return "/space/boy/detail";
	}
	@RequestMapping(value = "/cate", method = RequestMethod.GET)
	public String cateDaily(HttpServletRequest request,String cate , Model model){
		List<SpaceDailyModel> latestDailys =  spaceDailyService.iWantLatestDailys(5);
		model.addAttribute("LatestDailys", latestDailys);
		SongModel song = spaceDailyService.iWantASong();
		model.addAttribute("song", song);
		List<SpaceDailyModel> cateDailys = spaceDailyService.iWantCateDailys(cate);
		model.addAttribute("cateDailys", cateDailys);
		return "/space/boy/cate";
	}
	
}
