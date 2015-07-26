package wang.gagalulu.lovehouse.dao;

import java.util.Map;

import wang.gagalulu.lovehouse.bean.pojo.AnswerBean;

public interface QaDao {
	public AnswerBean getAnswerByQusition(Map<String,Object> params);
	public int save(AnswerBean answerBean)throws Exception;
}
