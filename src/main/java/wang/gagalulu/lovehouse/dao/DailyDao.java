package wang.gagalulu.lovehouse.dao;

import java.util.List;
import java.util.Map;

import wang.gagalulu.lovehouse.bean.pojo.DailyModel;

public interface DailyDao {
	public DailyModel getDailyById(Map<String,Object> params);
	public long save(DailyModel model)throws Exception;
	public void update(DailyModel model)throws Exception;
	public void delete(DailyModel model)throws Exception;
	public List<DailyModel> getDailyList(Map<String,Object> params);
	public int getAllCount();
}
