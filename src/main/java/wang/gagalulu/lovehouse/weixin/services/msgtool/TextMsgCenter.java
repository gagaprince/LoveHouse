package wang.gagalulu.lovehouse.weixin.services.msgtool;

import java.util.Date;

import org.dom4j.Element;

import wang.gagalulu.lovehouse.weixin.bean.msg.WXMsg;
import wang.gagalulu.lovehouse.weixin.bean.msg.WXTextMsg;

public class TextMsgCenter implements MsgCenter {
	private String executeMsgType;
	
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
		wxTextMsg.setContent(wxTextMsg.getContent()+" 收到了！");
		return wxTextMsg;
	}
	
	@Override
	public String getMsgType(){
		return executeMsgType;
	}
	
	public void setMsgType(String executeMsgType){
		this.executeMsgType=executeMsgType;
	}

}
