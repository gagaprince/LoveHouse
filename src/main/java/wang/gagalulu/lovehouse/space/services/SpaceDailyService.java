package wang.gagalulu.lovehouse.space.services;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wang.gagalulu.lovehouse.bean.pojo.SongModel;
import wang.gagalulu.lovehouse.bean.pojo.SpaceDailyModel;
import wang.gagalulu.lovehouse.dao.SongDao;
import wang.gagalulu.lovehouse.dao.SpaceDailyDao;

@Service
public class SpaceDailyService {
	@Autowired
	private SpaceDailyDao spaceDailyDao;
	@Autowired
	private SongDao songDao;
	
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
	
	public List<SpaceDailyModel> iWantLatestDailys(int num){
		int begin = 0;
		int end = num;
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("fromIndex", begin);
		params.put("toIndex", end);
		return spaceDailyDao.getDailyList(params);
	}
	
	public List<SpaceDailyModel> iWantCateDailys(String cate){
//		int psize=10;
//		int begin = pno*psize;
//		int end = begin + psize;
		int begin = 0;
		int end = Integer.MAX_VALUE;
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("cate", cate);
		params.put("fromIndex", begin);
		params.put("toIndex", end);
		return spaceDailyDao.getDailyList(params);
	}
	
	public SongModel iWantASong(){
		List<SongModel> songs = songDao.getAllSong();
		Random r = new Random();
		int index = r.nextInt(songs.size());
		return songs.get(index);
	}
}
