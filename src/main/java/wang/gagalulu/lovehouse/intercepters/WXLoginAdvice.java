package wang.gagalulu.lovehouse.intercepters;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import wang.gagalulu.lovehouse.util.CookieUtil;
import wang.gagalulu.lovehouse.weixin.bean.WXbasic;
import wang.gagalulu.lovehouse.weixin.config.WeiXinConfig;
import wang.gagalulu.lovehouse.weixin.services.WeiXinLoginService;

import com.alibaba.fastjson.JSONObject;

@Aspect
@Component
public class WXLoginAdvice {
	@Pointcut("@annotation(wang.gagalulu.lovehouse.annotation.WXLoginController)")
	public void wxLoginController(){}
	@Autowired
	private CookieUtil cookieUtil;
	@Autowired
	private WeiXinLoginService wxLoginService;
	@Autowired
	private WeiXinConfig wxConfig;
	
	private static final Logger logger =  Logger.getLogger(WXLoginAdvice.class);
	
	@Around("wxLoginController()")
	public void doAround(ProceedingJoinPoint  joinPoint) throws Throwable{
		Object[] args = joinPoint.getArgs();
		if(args.length>1){
			Object maybeReq = args[0];
			Object maybeRes = args[1];
			if(maybeReq instanceof HttpServletRequest && maybeRes instanceof HttpServletResponse){
				HttpServletRequest request = (HttpServletRequest)maybeReq;
				HttpServletResponse response = (HttpServletResponse)maybeRes;
				
				String token = cookieUtil.getCookie(request,"lovelulu_wx_token");
				String openId = cookieUtil.getCookie(request, "lovelulu_wx_openid");
				
				//有token逻辑
				
				if(token!=null&&openId!=null){
					if(setUserMsgIn(token, openId, request)){
						joinPoint.proceed();
						return;
					}
				}
				
				//token 失效 或者没有
				
				String code = request.getParameter("code");
				
				//code 逻辑
				if(code!=null){//已经获取code 需要通过code获取token
					String tokenMsg = wxLoginService.iWantLoginToken(code);
					if(tokenMsg!=null){
						JSONObject accessJson = JSONObject.parseObject(tokenMsg);
						token = (String)accessJson.get("access_token");
						if(token!=null){//正常返回token
							openId = setTokenCookie(accessJson,response);
							if(setUserMsgIn(token, openId, request)){
								joinPoint.proceed();
								return;
							}
						}
						//code 不正常
					}
				}
				//code失效 或者没有
				
				//跳转到指定的url
				sendRedirect(request,response);
			}
		}
	}
	
	private void sendRedirect(HttpServletRequest request,HttpServletResponse response){
		String redirectUrl = giveMeRedirectUrl(request);
		String authorizeUrl = giveMeAuthorizeUrl(redirectUrl);
		try {
			response.sendRedirect(authorizeUrl);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private String giveMeRedirectUrl(HttpServletRequest request){
		String domain = request.getParameter("domain");
		if(domain == null){
			domain = wxConfig.get("AppHost");
		}
		String nowUrl = "http://"+domain+request.getRequestURI();
		String search = request.getQueryString();
		if(search!=null){
			nowUrl += "?"+search;
		}
		logger.info("redirectUrl:"+nowUrl);
		return nowUrl;
	}
	
	private String giveMeAuthorizeUrl(String redirectUrl){
		try {
			redirectUrl = URLEncoder.encode(redirectUrl, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String authorizeUrl = wxConfig.getWxLoginAuthorizeUrl();
		WXbasic wxBasic = wxConfig.getWxBasic();
		String returnUrl = authorizeUrl+"?appid="+wxBasic.getAppId()+"&redirect_url="+redirectUrl+"&response_type=code&scope=snsapi_base&state="+new Random().nextInt(1000)+"#wechar_redirect";
		logger.info("authorizeUrl:"+returnUrl);
		return returnUrl;
	}
	
	
	private String setTokenCookie(JSONObject accessJson,HttpServletResponse response){
		String accessToken = (String)accessJson.get("access_token");
		int time = accessJson.getIntValue("expires_in");
		String refreshToken = accessJson.getString("refresh_token");
		String openid = accessJson.getString("openid");
		cookieUtil.addCookie(response, "lovelulu_wx_token", accessToken, "/",time);
		cookieUtil.addCookie(response, "lovelulu_wx_reToken", refreshToken, "/");
		cookieUtil.addCookie(response, "lovelulu_wx_openid", openid, "/");
		return openid;
	}
	
	private boolean setUserMsgIn(String token,String openId,HttpServletRequest request){
		String userMsg = wxLoginService.iWantUserMsg(token, openId);
		logger.info("userMsg:  "+userMsg);
		if(userMsg!=null){
			request.setAttribute("wx_user_msg", userMsg);
			return true;
		}
		return false;
	}
}
