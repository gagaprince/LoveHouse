package wang.gagalulu.lovehouse.dao;

import java.util.List;
import java.util.Map;

import wang.gagalulu.lovehouse.bean.pojo.SpaceDailyModel;

public interface SpaceDailyDao {
	public SpaceDailyModel getDailyById(Map<String,Object> params);
	public long save(SpaceDailyModel model)throws Exception;
	public void update(SpaceDailyModel model)throws Exception;
	public void delete(SpaceDailyModel model)throws Exception;
	public List<SpaceDailyModel> getDailyList(Map<String,Object> params);
	public int getAllCount();
}
