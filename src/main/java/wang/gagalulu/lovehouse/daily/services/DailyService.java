package wang.gagalulu.lovehouse.daily.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		List<DailyBean> dailyBeanList = new ArrayList<DailyBean>();
		
		JSONObject dailyContentJson = JSONObject.parseObject(dailyContent);
		String headTitle = dailyContentJson.getString("headTitle");
		String title = dailyContentJson.getString("title");
		String time = dailyContentJson.getString("time");
		String shareTitle = dailyContentJson.getString("shareTitle");
		String shareDesc = dailyContentJson.getString("shareDesc");
		String shareImg = dailyContentJson.getString("shareImg");
		String linkId = dailyContentJson.getString("linkId");
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
		contextBean.setShareTitle(shareTitle);
		contextBean.setShareDesc(shareDesc);
		contextBean.setShareImg(shareImg);
		contextBean.setLinkId(linkId);
		return contextBean;
	}
	
	public DailyModel saveOrUpdateDaily(DailyModel model) throws Exception{
		Long id = model.getId();
		model.setContent(replaceWhiteSpace(model.getContent()));
		if(id!=null){
			dailyDao.update(model);
		}else{
			model.setCreateTime(new Date());
			dailyDao.save(model);
		}
		return model;
	}
	
	public List<DailyModel> getDailyByPnoSize(int pno,int size){
		int fromIndex = pno*size;
		int toIndex = fromIndex+size;
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("fromIndex", fromIndex);
		params.put("toIndex", toIndex);
		return dailyDao.getDailyList(params);
	}
	
	public int getDailyCount(){
		return dailyDao.getAllCount();
	}
	
	private String replaceWhiteSpace(String dailyContent){
		return dailyContent.replace("  ", "<br>");
	}
	
	public void deleteDaily(DailyModel model) throws Exception{
		dailyDao.delete(model);
	}
}
