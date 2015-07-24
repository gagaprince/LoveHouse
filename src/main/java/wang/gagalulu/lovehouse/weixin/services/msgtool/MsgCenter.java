package wang.gagalulu.lovehouse.weixin.services.msgtool;

import org.dom4j.Element;

import wang.gagalulu.lovehouse.weixin.bean.msg.WXMsg;


public interface MsgCenter {
	public WXMsg executeMsg(Element root,WXMsg wxMsg);
	public String getMsgType();
}
