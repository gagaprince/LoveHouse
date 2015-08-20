package wang.gagalulu.lovehouse.dao;

import java.util.Map;

import wang.gagalulu.lovehouse.bean.pojo.DailyModel;

public interface DailyDao {
	public DailyModel getDailyById(Map<String,Object> params);
	public int save(DailyModel model)throws Exception;
}
