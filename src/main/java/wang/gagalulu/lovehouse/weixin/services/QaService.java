package wang.gagalulu.lovehouse.weixin.services;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wang.gagalulu.lovehouse.bean.pojo.AnswerBean;
import wang.gagalulu.lovehouse.dao.QaDao;
import wang.gagalulu.lovehouse.util.RegUtil;

@Service
public class QaService {
	@Autowired
	private QaDao qaDao;
	
	public AnswerBean getAnswer(String qu){
		RegUtil regUtil = RegUtil.getInstance();
		List<Map<String,String>> resultMapList = regUtil.regTest("问：(.*?)答：(.*)", qu, new String[]{"question","answer"});
		if(resultMapList.size()>0){//说明是teach模式
			Map<String,String> resultMap = resultMapList.get(0);
			String question = resultMap.get("question");
			String answer = resultMap.get("answer");
			return saveAnswer(question,answer);
		}else{
			return getAnswerByQuestion(qu);
		}
	}
	
	private AnswerBean saveAnswer(String question,String answer){
		AnswerBean answerBean = new AnswerBean();
		answerBean.setAnswer(answer);
		answerBean.setQuestion(question);
		answerBean.setInsertTime(new Date());
		try {
			qaDao.save(answerBean);
			answerBean.setAnswer("我已经学会了，不信你问问！");
		} catch (Exception e) {
			answerBean.setAnswer("没学会，再教一遍吧");
			e.printStackTrace();
		}
		return answerBean;
	}
	
	private AnswerBean getAnswerByQuestion(String question){
		HashMap<String,Object> params = new HashMap<String,Object>();
		params.put("question", question);
		AnswerBean answerBean = qaDao.getAnswerByQusition(params);
		if(answerBean==null){
			answerBean = new AnswerBean();
			answerBean.setAnswer("该怎么回答呢，教教我！");
		}
		return answerBean;
	}
}
