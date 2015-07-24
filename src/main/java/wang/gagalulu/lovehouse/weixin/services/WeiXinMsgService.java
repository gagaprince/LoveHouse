package wang.gagalulu.lovehouse.weixin.services;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class WeiXinMsgService {
	private static final Logger logger =  Logger.getLogger(WeiXinMsgService.class);
	
	public String doExcuteMsg(HttpServletRequest request,String xml){
		logger.info("接收到的xml:"+xml);
		return "";
	}
}
