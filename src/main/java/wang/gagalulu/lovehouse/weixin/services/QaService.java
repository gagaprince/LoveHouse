package wang.gagalulu.lovehouse.weixin.services;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wang.gagalulu.lovehouse.bean.pojo.AnswerBean;
import wang.gagalulu.lovehouse.dao.QaDao;
import wang.gagalulu.lovehouse.luceneindex.services.QaSearchService;
import wang.gagalulu.lovehouse.util.RegUtil;

@Service
public class QaService {
	@Autowired
	private QaDao qaDao;
	@Autowired
	private QaSearchService qaSearchService;
	
	public AnswerBean getAnswer(String qu){
		RegUtil regUtil = RegUtil.getInstance();
		List<Map<String,String>> resultMapList = regUtil.regTest("问：(.*?)答：(.*)", qu, new String[]{"question","answer"});
		if(resultMapList.size()>0){//说明是teach模式
			Map<String,String> resultMap = resultMapList.get(0);
			String question = resultMap.get("question");
			String answer = resultMap.get("answer");
			return saveAnswer(question,answer);
		}else{
			try {
				return qaSearchService.IWantOneAnswer(qu);
			} catch (CorruptIndexException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			AnswerBean answerBean = new AnswerBean();
			answerBean.setAnswer("");
			return answerBean;
//			return getAnswerByQuestion(qu);
		}
	}
	
	private AnswerBean saveAnswer(String question,String answer){
		AnswerBean answerBean = new AnswerBean();
		answerBean.setAnswer(answer);
		answerBean.setQuestion(question);
		answerBean.setInsertTime(new Date());
		try {
			qaDao.save(answerBean);
			qaSearchService.addOneQaInIndex(answerBean);
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
		List<AnswerBean> answerBeanList = qaDao.getAnswerByQusition(params);
		AnswerBean answerBean;
		if(answerBeanList==null||answerBeanList.size()==0){
			answerBean = new AnswerBean();
			answerBean.setAnswer("");
		}else{
			int index = new Random().nextInt(answerBeanList.size());
			answerBean = answerBeanList.get(index);
		}
		return answerBean;
	}
	
	public String getDefaultAnswer(){
		return "该怎么回答呢？你教教我吧~ 回复“ 问：你几岁了？答：今年1岁了！ ”如此句式，教我学话吧~";
	}
}
