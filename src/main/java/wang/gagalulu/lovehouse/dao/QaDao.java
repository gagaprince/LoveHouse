package wang.gagalulu.lovehouse.dao;

import java.util.List;
import java.util.Map;

import wang.gagalulu.lovehouse.bean.pojo.AnswerBean;

public interface QaDao {
	public List<AnswerBean> getAnswerByQusition(Map<String,Object> params);
	public List<AnswerBean> getAnswerList(Map<String,Object> params);
	public int save(AnswerBean answerBean)throws Exception;
	public long getAllCount();
}
