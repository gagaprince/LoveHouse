package wang.gagalulu.lovehouse.weixin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;

import wang.gagalulu.lovehouse.util.CookieUtil;
import wang.gagalulu.lovehouse.weixin.services.WeiXinLoginService;

@Controller
@RequestMapping("/lovelulu/wxlogin")
public class WXLoginController {
	private static final Logger logger =  Logger.getLogger(WXLoginController.class);
	@Autowired
	private WeiXinLoginService wxLoginService;
	@Autowired
	private CookieUtil cookieUtil;
	
	public String reqLoginAccessToken(HttpServletRequest request,HttpServletResponse response,String code){
		logger.info("获取网页登录的access_token，code："+code);
		String result = wxLoginService.iWantLoginToken(code);
		JSONObject accessJson = JSONObject.parseObject(result);
		String accessToken = (String)accessJson.get("access_token");
		if(accessToken!=null){//获取token成功 写cookie
			int time = accessJson.getIntValue("expires_in");
			cookieUtil.addCookie(response, "lovelulu_wx_token", accessToken, "/",time);
			String refreshToken = accessJson.getString("refresh_token");
			String openid = accessJson.getString("openid");
			cookieUtil.addCookie(response, "lovelulu_wx_reToken", refreshToken, "/");
			cookieUtil.addCookie(response, "lovelulu_wx_openid", openid, "/");
			
			result = wxLoginService.iWantUserMsg(accessToken, openid);
		}
		
		return result;
	}
}
