package wang.gagalulu.lovehouse.weixin.services.msgtool;

import java.util.Date;

import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;

import wang.gagalulu.lovehouse.bean.pojo.AnswerBean;
import wang.gagalulu.lovehouse.weixin.bean.msg.WXMsg;
import wang.gagalulu.lovehouse.weixin.bean.msg.WXTextMsg;
import wang.gagalulu.lovehouse.weixin.services.QaService;

public class TextMsgCenter implements MsgCenter {
	private String executeMsgType;
	@Autowired
	private QaService qaService;
	
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
		String repContent = getRepContent(wxTextMsg.getContent());
		wxTextMsg.setContent(repContent);
		return wxTextMsg;
	}
	private String getRepContent(String qu){
		AnswerBean asbean = qaService.getAnswer(qu);
		return asbean.getAnswer();
	}
	
	@Override
	public String getMsgType(){
		return executeMsgType;
	}
	
	public void setMsgType(String executeMsgType){
		this.executeMsgType=executeMsgType;
	}

}
