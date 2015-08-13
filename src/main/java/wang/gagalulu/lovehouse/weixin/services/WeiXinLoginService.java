package wang.gagalulu.lovehouse.weixin.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wang.gagalulu.lovehouse.util.HttpUtil;
import wang.gagalulu.lovehouse.weixin.bean.WXbasic;
import wang.gagalulu.lovehouse.weixin.config.WeiXinConfig;

@Service
public class WeiXinLoginService {
	private static final Logger logger =  Logger.getLogger(WeiXinLoginService.class);
	@Autowired
	private WeiXinConfig wxConfig;
	public String iWantLoginToken(String code){
		String loginMsgUrl = giveMeLoginUrl(code);
		logger.info("请求logintoken的url："+loginMsgUrl);
		String result = HttpUtil.getContentByUrl(loginMsgUrl);
		logger.info("获得logintoken的content："+result);
		return result;
	}
	
	
	public String iWantUserMsg(String token,String openId){
		String userMsgUrl = giveMeUserMsgUrl(token,openId);
		logger.info("请求用户信息的的url："+userMsgUrl);
		String result = HttpUtil.getContentByUrl(userMsgUrl);
		logger.info("获得用户信息的content："+result);
		return result;
	}
	
	//组装获取logintoken的url
	private String giveMeLoginUrl(String code){
		WXbasic wxBasic = wxConfig.getWxBasic();
		String returnUrl = wxConfig.getLoginAccessTokenUrl();
		returnUrl = returnUrl+"?appid="+wxBasic.getAppId()+"&secret="+wxBasic.getAppSecret()+"&code="+code+"&grant_type=authorization_code";
		return returnUrl;
	}
	
	private String giveMeUserMsgUrl(String token ,String openId){
		String returnUrl = wxConfig.getUserMsgUrl();
		returnUrl = returnUrl + "?access_token="+token+"&openid="+openId+"&lang=zh_CN";
		return returnUrl;
	}
}
