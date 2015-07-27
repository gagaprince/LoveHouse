package wang.gagalulu.lovehouse.dao;

import java.util.List;
import java.util.Map;

import wang.gagalulu.lovehouse.bean.pojo.DuanZiModel;

public interface DuanziDao {
	public DuanZiModel getDuanZiById(Map<String,Object> params);
	public DuanZiModel getDuanZiByFromeId(Map<String,Object> params);
	public List<DuanZiModel> getDuanZiList(Map<String,Object> params);
	public int save(DuanZiModel model)throws Exception;
	public long getAllCount();
}
