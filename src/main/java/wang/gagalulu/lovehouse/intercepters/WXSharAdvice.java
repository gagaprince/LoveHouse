package wang.gagalulu.lovehouse.intercepters;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import wang.gagalulu.lovehouse.weixin.bean.WXShareConfigModel;
import wang.gagalulu.lovehouse.weixin.services.WeiXinShareService;

import com.alibaba.fastjson.JSON;

@Aspect
@Component
public class WXSharAdvice {
	@Autowired
	private WeiXinShareService wxShareService;
	
	
	@Pointcut("@annotation(wang.gagalulu.lovehouse.annotation.WXShareController)")
	public void wxShareController(){}
	
	@Before("wxShareController()")
	public void doBefore(JoinPoint joinPoint){
		Object[] args = joinPoint.getArgs();
		if(args.length>0){
			Object arg = args[0];
			if(arg instanceof HttpServletRequest){
				HttpServletRequest request = (HttpServletRequest)arg;
				WXShareConfigModel shareModel = wxShareService.getCanUseShareConfig(request);
				request.setAttribute("wxShareConfig", JSON.toJSON(shareModel));
			}
		}
		
	}
}
