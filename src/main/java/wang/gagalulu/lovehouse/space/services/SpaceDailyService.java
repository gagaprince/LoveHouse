package wang.gagalulu.lovehouse.space.services;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wang.gagalulu.lovehouse.bean.pojo.SpaceDailyModel;
import wang.gagalulu.lovehouse.dao.SpaceDailyDao;

@Service
public class SpaceDailyService {
	@Autowired
	private SpaceDailyDao spaceDailyDao;
	
	public SpaceDailyModel saveOrUpdateDaily(SpaceDailyModel model) throws Exception{
		Long id = model.getId();
		if(id!=null){
			spaceDailyDao.update(model);
		}else{
			model.setCreateTime(new Date());
			spaceDailyDao.save(model);
		}
		return model;
	}
	
	public SpaceDailyModel iWantADaily(long id){
		HashMap<String,Object> params = new HashMap<String,Object>();
		params.put("id", id);
		SpaceDailyModel dailyModel = spaceDailyDao.getDailyById(params);
		return dailyModel;
	}
}
