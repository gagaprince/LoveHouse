package wang.gagalulu.lovehouse.daily.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wang.gagalulu.lovehouse.bean.pojo.DailyModel;
import wang.gagalulu.lovehouse.daily.bean.DailyBean;
import wang.gagalulu.lovehouse.daily.bean.DailyContextBean;
import wang.gagalulu.lovehouse.dao.DailyDao;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Service
public class DailyService {
	private static final Logger logger =  Logger.getLogger(DailyService.class);
	@Autowired
	private DailyDao dailyDao;
	
	public DailyModel iWantADaily(long id){
		HashMap<String,Object> params = new HashMap<String,Object>();
		params.put("id", id);
		DailyModel dailyModel = dailyDao.getDailyById(params);
		return dailyModel;
	}
	
	public DailyContextBean iWantParseDailyModel(DailyModel dailyModel){
		String dailyContent = dailyModel.getContent();
		logger.info(dailyContent);
		List<DailyBean> dailyBeanList = new ArrayList<DailyBean>();
		
		JSONObject dailyContentJson = JSONObject.parseObject(dailyContent);
		String headTitle = dailyContentJson.getString("headTile");
		String title = dailyContentJson.getString("title");
		String time = dailyContentJson.getString("time");
		JSONArray dailyContents = dailyContentJson.getJSONArray("content");
		int length = dailyContents.size();
		for(int i=0;i<length;i++){
			JSONObject dailyContentObj = dailyContents.getJSONObject(i);
			String type = dailyContentObj.getString("type");
			String content = dailyContentObj.getString("content");
			DailyBean dailyBean = new DailyBean(type, content);
			dailyBeanList.add(dailyBean);
		}
		DailyContextBean contextBean = new DailyContextBean();
		contextBean.setTitle(title);
		contextBean.setHeadTitle(headTitle);
		contextBean.setTime(time);
		contextBean.setDailyList(dailyBeanList);	
		return contextBean;
	}
}
