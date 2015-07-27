package wang.gagalulu.lovehouse.weixin.services.msgtool;

import java.io.IOException;
import java.util.Date;

import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.queryParser.ParseException;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;

import wang.gagalulu.lovehouse.bean.pojo.AnswerBean;
import wang.gagalulu.lovehouse.luceneindex.services.DuanziService;
import wang.gagalulu.lovehouse.weixin.bean.msg.WXMsg;
import wang.gagalulu.lovehouse.weixin.bean.msg.WXTextMsg;
import wang.gagalulu.lovehouse.weixin.services.QaService;

public class TextMsgCenter implements MsgCenter {
	private String executeMsgType;
	@Autowired
	private QaService qaService;
	@Autowired
	private DuanziService duanziService;
	
	@Override
	public WXMsg executeMsg(Element root,WXMsg wxMsg) {
		if(executeMsgType==null || !executeMsgType.equals(wxMsg.getMsgType()))return null;
		
		String content = root.elementText("Content");
		WXTextMsg wxTextMsg = new WXTextMsg(wxMsg,content);
		return giveMeARepByMsg(wxTextMsg);
	}
	
	private WXMsg giveMeARepByMsg(WXTextMsg wxTextMsg){
		String toUser = wxTextMsg.getToUserName();
		wxTextMsg.setToUserName(wxTextMsg.getFromUserName());
		wxTextMsg.setFromUserName(toUser);
		wxTextMsg.setCreateTime(new Date().getTime()+"");
		String repContent = iNeedRep(wxTextMsg.getContent());
		
		wxTextMsg.setContent(repContent);
		return wxTextMsg;
	}
	
	private String iNeedRep(String key){
		String result = "";
		result = getAnswerContent(key);
		if("".equals(result)){
			result = getDuanziContent(key);
		}
		if("".equals(result)){
			result = getDefaultAnswerContent();
		}
		return result;
	}
	
	private String getAnswerContent(String qu){
		AnswerBean asbean = qaService.getAnswer(qu);
		return asbean.getAnswer();
	}
	
	private String getDefaultAnswerContent(){
		return qaService.getDefaultAnswer();
	}
	
	private String getDuanziContent(String key){
		try {
			return duanziService.IWantOneDuanzi(key);
		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	@Override
	public String getMsgType(){
		return executeMsgType;
	}
	
	public void setMsgType(String executeMsgType){
		this.executeMsgType=executeMsgType;
	}

}
